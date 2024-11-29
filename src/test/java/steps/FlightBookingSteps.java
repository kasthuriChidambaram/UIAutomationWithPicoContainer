package steps;

import io.cucumber.java.en.When;
import pages.FlightBookingPage;

public class FlightBookingSteps {

    private FlightBookingPage flightBookingPage;

    public FlightBookingSteps(FlightBookingPage flightBookingPage){
        this.flightBookingPage = flightBookingPage;

    }
    @When("the user provides the required personal information")
    public void userProceedsWithBooking() {
        flightBookingPage.proceedWithBooking();
    }
}
