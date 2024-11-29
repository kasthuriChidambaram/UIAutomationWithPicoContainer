package pages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import support.DriverUtil;

public class FlightBookingSearchPage {

    private DriverUtil driverUtil;
    private static final Logger logger = LoggerFactory.getLogger(FlightBookingSearchPage.class);

    public FlightBookingSearchPage( DriverUtil driverUtil) {
        this.driverUtil = driverUtil;
    }

    public void selectFirstFlight() {
        //driverUtil.handleOptionalPopup("fair_lock_container_xpath", "Fair lock container");
        driverUtil.handleOptionalPopup("flight_comparison_popup_xpath", "Flight comparison popup");

        // Proceed to click on 'view prices' after handling popups
        driverUtil.getElement("view_prices_css").click();
        logger.info("Clicked on 'View Prices'");
    }



}
