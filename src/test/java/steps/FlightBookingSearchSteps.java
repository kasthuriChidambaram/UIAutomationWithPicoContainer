package steps;

import io.cucumber.java.en.When;
import pages.FlightBookingSearchPage;

public class FlightBookingSearchSteps {

    private FlightBookingSearchPage flightBookingSearchPage;

    public  FlightBookingSearchSteps(FlightBookingSearchPage flightBookingSearchPage){
        this.flightBookingSearchPage = flightBookingSearchPage;
    }
    @When("the user selects the first available flight")
    public void userSelectsFirstAvailableFlight() {
        flightBookingSearchPage.selectFirstFlight();
    }


}
