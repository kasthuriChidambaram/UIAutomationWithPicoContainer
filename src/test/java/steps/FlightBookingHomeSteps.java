package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.FlightBookingSearchPage;
import pages.FlightBookingHomePage;

public class FlightBookingHomeSteps {

        private FlightBookingHomePage flightBookingHomePage;
        private FlightBookingSearchPage flightBookingSearchPage;

        public FlightBookingHomeSteps(FlightBookingHomePage flightBookingHomePage, FlightBookingSearchPage flightBookingSearchPage) {
            this.flightBookingHomePage = flightBookingHomePage;
            this.flightBookingSearchPage =flightBookingSearchPage;
        }

        @Given("the user is on the MakeMyTrip flight booking page")
        public void userOnFlightBookingPage() {
            flightBookingHomePage.goToFlightBookingPage();
        }

        @When("the user searches for flights from {string} to {string}")
        public void userSearchesForFlights(String from, String to) throws InterruptedException {
            flightBookingHomePage.searchFlights(from, to);
        }

        @Then("the user should see the booking confirmation page")
        public void userSeesConfirmationPage() {
            flightBookingHomePage.verifyBookingConfirmation();
        }
    }


