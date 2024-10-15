package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.FlightBookingMobilePage;
import pages.FlightBookingPage;

public class FlightBookingSteps {

        private FlightBookingPage flightBookingPage;
        private FlightBookingMobilePage flightBookingMobilePage;

        public FlightBookingSteps(FlightBookingPage flightBookingPage,FlightBookingMobilePage flightBookingMobilePage) {
            this.flightBookingPage = flightBookingPage;
            this.flightBookingMobilePage=flightBookingMobilePage;
        }

        @Given("the user is on the MakeMyTrip flight booking page")
        public void userOnFlightBookingPage() {
            flightBookingPage.goToFlightBookingPage();
        }

        @When("the user searches for flights from {string} to {string}")
        public void userSearchesForFlights(String from, String to) throws InterruptedException {
            flightBookingPage.searchFlights(from, to);
        }

        @When("the user selects the first available flight")
        public void userSelectsFirstAvailableFlight() {
            flightBookingPage.selectFirstFlight();
        }

        @When("the user proceeds with booking")
        public void userProceedsWithBooking() {
            flightBookingPage.proceedWithBooking();
        }

        @Then("the user should see the booking confirmation page")
        public void userSeesConfirmationPage() {
            flightBookingPage.verifyBookingConfirmation();
        }
    }


