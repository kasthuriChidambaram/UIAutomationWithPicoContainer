package support;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.function.Function;

public class DriverUtil {
    private WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(DriverUtil.class);

    public DriverUtil(WebDriverManagerUtil webDriverManager) {
        this.driver = webDriverManager.getDriver();
    }


    public Properties loadLocatorProperties() {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("src/test/resources/locators.properties")) {
            properties.load(fis);
        } catch (IOException e) {

        }
        return properties;
    }

    public void handleOptionalPopup(String locator, String popupName) {
        try {
            getElement(locator).click();
            logger.info(popupName + " clicked.");
        } catch (Exception e) {
            logger.warn(popupName + " not found or could not be clicked: " + e.getMessage());
        }
    }

    public void switchToNextNewWindow() {
        Object[] windowHandles = driver.getWindowHandles().toArray();
        driver.switchTo().window((String) windowHandles[1]);
    }

    public String getCurrentWindow() {
        return driver.getWindowHandle();
    }

    public WebElement scrollToElement(String key, int xAxis, int yAxis) {
        WebElement element = getElement(key);
        int deltaY = element.getRect().y;
        new Actions(driver).scrollByAmount(xAxis, yAxis).perform();

        return element;
    }

    public WebElement scrollToElementLocation(String key, int xAxis) {
        while (driver.findElements(By.xpath(key)).isEmpty()) {
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 200);");
            try {
                Thread.sleep(5000); // Optional pause to allow content to load
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        WebElement element = driver.findElement(By.xpath(key));
        return element;
    }


    public void actionsClick(String key) {
        Actions actions = new Actions(driver);
        WebElement element = getVisibleElement(key);
        actions.moveToElement(element).click(element).build().perform();

    }

    public void actionsContextClick(String key) {
        Actions actions = new Actions(driver);
        WebElement element = getVisibleElement(key);
        actions.moveToElement(element).contextClick(element).build().perform();

    }

    //This worked for flex elements in chrome.
    public void jsClick(String key) {
        WebElement element = getVisibleElement(key);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    public void jsClick(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    //Focus-Based Event Listeners:
    //Many web applications, especially those built with JavaScript frameworks, may not respond to the default click()
    //method provided by Selenium.
    //Instead, they might listen for keyboard events (like pressing the Enter or Space key) or focus events
    //(when an element gains focus) to open or trigger dropdown menus.
    public void clickBySendKeys(String key) {
        WebElement dropdown = getVisibleElement(key);
        dropdown.sendKeys(Keys.ARROW_DOWN);

    }

    public void sleep(int timeToSleep) {
        try {
            Thread.sleep(timeToSleep);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public WebElement getElement(String key) {
        try {
            WebElement element = waitForElement(key, ExpectedConditions::elementToBeClickable);
            Assert.assertNotNull("Element not found: " + key, element);
            return element;
        } catch (TimeoutException | NoSuchElementException e) {
            logger.error("Element not found for key: " + key, e);
            throw e;
        }
    }

    public WebElement getVisibleElement(String key) {
        try {
            WebElement element = waitForElement(key, ExpectedConditions::visibilityOfElementLocated);
            Assert.assertNotNull("Element not found: " + key, element);
            return element;
        } catch (TimeoutException | NoSuchElementException e) {
            logger.error("Element not found for key: " + key, e);
            throw e;
        }
    }

    public List<WebElement> getElements(String key) {
        try {
            List<WebElement> elements = waitForElement(key, ExpectedConditions::visibilityOfAllElementsLocatedBy);
            Assert.assertNotNull("Elements not found: " + key, elements);
            return elements;
        } catch (TimeoutException | NoSuchElementException e) {
            logger.error("Elements not found for key: " + key, e);
            throw e;
        }
    }

    private <T> T waitForElement(String key, Function<By, ExpectedCondition<T>> conditionFunction) {
        Properties locatorProperties = loadLocatorProperties();
        String locator = locatorProperties.getProperty(key);
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(60))
                .pollingEvery(Duration.ofSeconds(2));

        By by;
        if (key.endsWith("css")) {
            by = By.cssSelector(locator);
        } else if (key.endsWith("xpath")) {
            by = By.xpath(locator);
        } else if (key.endsWith("id")) {
            by = By.id(locator);
        } else {
            throw new IllegalArgumentException("Unknown locator type for key: " + key);
        }

        return wait.until(conditionFunction.apply(by));
    }

    public WebElement getDynamicElement(String locator, String key) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(60))
                .pollingEvery(Duration.ofSeconds(2));

        try {
            By by = getLocatorByType(locator, key);
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
            Assert.assertNotNull("Element not found: " + key, element); // Optional assertion
            return element;
        } catch (TimeoutException | NoSuchElementException e) {
            logger.error("Element not found for locator: " + locator + " and key: " + key, e);
            throw e;
        }
    }

    private By getLocatorByType(String locator, String key) {
        if (key.endsWith("css")) {
            return By.cssSelector(locator);
        } else if (key.endsWith("xpath")) {
            return By.xpath(locator);
        } else {
            throw new IllegalArgumentException("Unknown locator type for key: " + key);
        }
    }



}
