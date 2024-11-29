package steps;

import io.cucumber.java.en.When;
import pages.FlightBookingSeatSelectionPage;

public class FlightBookingSeatSelectionSteps {

    private FlightBookingSeatSelectionPage flightBookingSeatSelectionPage;

    public FlightBookingSeatSelectionSteps(FlightBookingSeatSelectionPage flightBookingSeatSelectionPage) {
        this.flightBookingSeatSelectionPage = flightBookingSeatSelectionPage;
    }

    @When("the user selects the seat and continue with payment")
    public void userSelectsAvailableSeat() {
        flightBookingSeatSelectionPage.selectSeat();
        flightBookingSeatSelectionPage.continuePaymentPage();

    }

}
