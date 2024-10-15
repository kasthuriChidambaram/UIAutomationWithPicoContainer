@Booking
Feature: Book a flight on MakeMyTrip

  Scenario: User books a one-way flight
    Given the user is on the MakeMyTrip flight booking page
    When the user searches for flights from "Bangalore" to "Delhi"
    And the user selects the first available flight
    And the user proceeds with booking
    Then the user should see the booking confirmation page
