package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import support.DriverUtil;
import support.WebDriverManagerUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class FlightBookingPage {

    private WebDriver driver;
    private String url;

    private DriverUtil driverUtil;

    public FlightBookingPage(WebDriverManagerUtil webDriverManager, DriverUtil driverUtil) {
        this.driver = webDriverManager.getDriver();
        this.url = webDriverManager.loadConfigProperties().getProperty("url");
        this.driverUtil = driverUtil;
    }

    public void goToFlightBookingPage() {
        driver.get(url);
        driverUtil.getElement("login_popup_css").click();

    }

    public void searchFlights(String from, String to)  {
        driverUtil.getElement("from_city_input_css").click();
        driverUtil.getElement("from_input_css").sendKeys(from);
        driverUtil.getElement("select_city_from_xpath").click();

        driverUtil.getElement("to_city_input_css").click();
        driverUtil.getElement("to_input_css").sendKeys(to);
        driverUtil.getElement("select_city_to_xpath").click();

        String formattedDate = getBookingDate();
        String locator = String.format("[aria-label='%s']", formattedDate);

        driverUtil.getDynamicElement(locator,"css").click();
        Actions actions = new Actions(driver);

        WebElement webelement= driverUtil.getElement("search_flight_css");
        actions.click(webelement).build().perform();


    }

    public void selectFirstFlight() {
    }

    public void proceedWithBooking() {

    }

    public void verifyBookingConfirmation() {

    }

    private static String getBookingDate(){
        LocalDate today = LocalDate.now();
        // Add 15 days to today's date
        LocalDate futureDate = today.plus(15, ChronoUnit.DAYS);
        // Define the pattern to format the date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd yyyy", Locale.ENGLISH);
        // Format the date and print
        String formattedDate = futureDate.format(formatter);
        return formattedDate;
    }


}


