@Booking
Feature: Book a flight on MakeMyTrip

  Scenario: User books a one-way flight
    Given the user is on the MakeMyTrip flight booking page
    When the user searches for flights from "Bangalore" to "Delhi"
    And the user selects the first available flight
    And the user provides the required personal information
    And the user selects the seat and continue with payment
    Then the user should see the payment details page
      | UPI Options            |
      | Credit/Debit/ATM Card  |
      | EMI                    |
      | Net Banking            |
      | Book Now Pay Later     |
      | Gift Cards & e-wallets |
