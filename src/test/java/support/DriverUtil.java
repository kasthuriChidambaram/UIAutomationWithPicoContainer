package support;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class DriverUtil {
    private WebDriver driver;
    public DriverUtil(WebDriverManagerUtil webDriverManager) {
        this.driver = webDriverManager.getDriver();
    }


    private Properties loadLocatorProperties() {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("src/test/resources/locators.properties")) {
            properties.load(fis);
        } catch (IOException e) {

        }
        return properties;
    }

    public WebElement getElement(String key) {
        Properties locatorProperties = loadLocatorProperties();
        String locator = locatorProperties.getProperty(key);
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(2));

        try {
            WebElement element = wait.until(webDriver -> {
                if (key.endsWith("id")) {
                    return wait.until(ExpectedConditions.elementToBeClickable(By.id(locator)));
                } else if (key.endsWith("css")) {
                    return wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(locator)));
                } else if (key.endsWith("xpath")) {
                    return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
                } else {
                    throw new IllegalArgumentException("Unknown locator type for key: " + key);
                }
            });

            // Assert that element is not null (optional)
            Assert.assertNotNull("Element not found: " + key, element);
            return element;
        } catch (NoSuchElementException e) {
            System.out.println("Element not found for key: " + key);
            throw e;
        }
    }

    public WebElement getDynamicElement(String locator,String key) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(2));

        try {
            WebElement element = wait.until(webDriver -> {
                if (key.endsWith("id")) {
                    return wait.until(ExpectedConditions.elementToBeClickable(By.id(locator)));
                } else if (key.endsWith("css")) {
                    return wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(locator)));
                } else if (key.endsWith("xpath")) {
                    return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
                } else {
                    throw new IllegalArgumentException("Unknown locator type for key: " + key);
                }
            });

            // Assert that element is not null (optional)
            Assert.assertNotNull("Element not found: " + key, element);
            return element;
        } catch (NoSuchElementException e) {
            System.out.println("Element not found for key: " + key);
            throw e;
        }
    }

}
