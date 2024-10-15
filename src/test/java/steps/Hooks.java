package steps;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;

import support.WebDriverManagerUtil;

public class Hooks {
    private WebDriver driver;

    public Hooks(WebDriverManagerUtil webDriverManager) {
        this.driver = webDriverManager.getDriver();
    }

    @After
    public void tearDown(Scenario scenario) {
        //driver.quit();// Quit the driver after all tests
    }


}

