package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import pages.FlightBookingPaymentPage;

import java.util.List;

public class FlightBookingPaymentSteps {

    private FlightBookingPaymentPage flightBookingPaymentPage;
    public FlightBookingPaymentSteps(FlightBookingPaymentPage flightBookingPaymentPage){
        this.flightBookingPaymentPage = flightBookingPaymentPage;

    }
    @Then("the user should see the payment details page")
    public void userSeesPaymentOptions(DataTable table) {
        List<String> options = table.asList();
        flightBookingPaymentPage.assertPaymentOptions(options);

    }
}
