package pages;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import support.DriverUtil;

public class FlightBookingSeatSelectionPage {
    private DriverUtil driverUtil;
    private static final Logger logger = LoggerFactory.getLogger(FlightBookingSearchPage.class);

    public FlightBookingSeatSelectionPage(DriverUtil driverUtil) {
        this.driverUtil = driverUtil;
    }

    public void selectSeat() {
        // Fetch the 'id' attribute value
        String seatId = driverUtil.getElement("seat_id_css").getAttribute("id");
        String fixedSelectorPrefix = seatId.split("_")[0] + "_"; // Extracts up to "Flightnumber"
        int totalSeatRows = 34;
        String[] windowSeats = {"A", "F"};

        // Loop through all rows
        for (int row = 1; row <= totalSeatRows; row++) {
            // Loop through window seats for each row
            for (String windowSeat : windowSeats) {
                String windowSeatID = row + windowSeat; // e.g., "1A", "1F", etc.
                String seatSelector = "//*[@id='" + fixedSelectorPrefix + windowSeatID + "']//div"; // XPath selector
                // Get the class attribute
                String seatClass = driverUtil.getDynamicElement(seatSelector, "xpath").getAttribute("class");
                // Perform actions based on seat availability
                if ("seat not available".equalsIgnoreCase(seatClass)) {
                    logger.info("Seat " + windowSeatID + " is already booked.");
                } else if ("seatBlock pointer".equalsIgnoreCase(seatClass)) {
                    WebElement element = driverUtil.getDynamicElement(seatSelector, "xpath");
                    driverUtil.jsClick(element);
                    logger.info("Seat " + windowSeatID + " has been booked.");
                    return; // Exit the method after booking a seat
                }
            }
        }
    }


    public void continuePaymentPage() {
        driverUtil.getElement("continue_meal_selection_css").click();
        driverUtil.getElement("continue_airport_pick_up_drop_css").click();
        driverUtil.getElement("continue_payment_css").click();
    }
}