package pages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import support.DriverUtil;

public class FlightBookingPage {

    private DriverUtil driverUtil;
    private static final Logger logger = LoggerFactory.getLogger(FlightBookingSearchPage.class);

    private String firstname = "Michel";
    private String lastname = "stephen";
    private String mobilenumber = "8767850034";
    private String email = "flightbookingoctober@mailinator.com";

    public FlightBookingPage(DriverUtil driverUtil) {
        this.driverUtil = driverUtil;
    }

    public void proceedWithBooking() {
        driverUtil.getElement("book_now_css").click();
        driverUtil.switchToNextNewWindow();
        addInsuranceDetails();
        addTravellerPersonalDetails();
        addTravellerState();
        driverUtil.jsClick("review_details_css");
        driverUtil.jsClick("choosing_aisle_popup_css");
    }

    private void addTravellerState() {
        driverUtil.scrollToElement("state_xpath",0,150);
        driverUtil.sleep(5000);
        driverUtil.clickBySendKeys("state_xpath");
        driverUtil.jsClick("select_state_xpath");
        driverUtil.jsClick("save_bill_details_css");
        driverUtil.jsClick("continue_css");
    }

    private void addTravellerPersonalDetails() {
        driverUtil.sleep(5000);
        driverUtil.getElement("add_traveller_css").click();
        driverUtil.getElement("firstname_css").sendKeys(firstname);
        driverUtil.getElement("lastname_css").sendKeys(lastname);
        driverUtil.getElement("mobile_no_css").sendKeys(mobilenumber);
        driverUtil.getElement("email_css").sendKeys(email);
        driverUtil.jsClick("gender_css");
    }

    private  void addInsuranceDetails(){
        driverUtil.sleep(5000);
        driverUtil.getVisibleElement("book_without_insurance_xpath").click();
    }


}
