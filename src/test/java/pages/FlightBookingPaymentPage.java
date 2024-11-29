package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import support.DriverUtil;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class FlightBookingPaymentPage {
    private DriverUtil driverUtil;

    public FlightBookingPaymentPage(DriverUtil driverUtil) {
        this.driverUtil = driverUtil;

    }

    public void assertPaymentOptions(List<String> expectedOptions) {

        // Dynamically retrieve all the list items under the payment options container
        List<WebElement> paymentOptions = driverUtil.getElements("payment_options_css");

        // Assert that the number of payment options matches the expected size
        Assert.assertEquals("Number of payment options does not match.", expectedOptions.size(), paymentOptions.size());

        // Iterate through each list item and validate its text
        for (int i = 0; i < paymentOptions.size(); i++) {
            // Construct a dynamic CSS selector for each nth-child element
            String optionText = paymentOptions.get(i).findElement(By.cssSelector("div > span:nth-child(1)")).getText().trim();

            // Compare with the corresponding expected option
            Assert.assertEquals("Payment option does not match at index " + (i + 1), expectedOptions.get(i).trim(), optionText);
        }
    }
}
