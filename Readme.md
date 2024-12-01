# Selenium Cucumber Java Project
This project automates the flight booking process on the website using Selenium, Cucumber, and Java. The project supports dependency injection with PicoContainer, parallel execution, multiple browsers, and is integrated with Jenkins for CI/CD.

# Project Setup
## Prerequisites
Java 8 installed.
Maven for building and managing dependencies.
Jenkins (optional) for CI/CD integration.

## Clone the Repository
Clone the repository and navigate to the project folder to begin working with the project.

## Install Dependencies
Use Maven to install the necessary project dependencies.

## Features
1. Automates the flight booking process.
2. Supports Chrome, Firefox, and Edge browsers.
3. Executes tests in parallel.
4. Manages dependencies like WebDriver using PicoContainer.
5.Integrated with Jenkins for automated CI/CD.

## Technologies Used
1. Selenium WebDriver for automating browsers.
2. Cucumber for BDD.
3. PicoContainer for dependency management.
4. Maven for project management.
5. Jenkins for continuous integration.

## Dependency Injection with PicoContainer
The project uses PicoContainer to manage and inject shared dependencies, such as WebDriver, across step definition classes, ensuring seamless integration and state management during test execution.

## Locators Management
All locators for elements on the website are managed in a separate file for better maintainability and easier updates, should the UI of the website change.

## Running Tests Locally
Tests can be executed locally using Maven. The browser for test execution can be specified as a command-line argument, allowing flexibility to run tests across different browsers.

mvn clean test -Dbrowser=chrome

## Jenkins Integration
The project is integrated with Jenkins for continuous integration. A Jenkinsfile is included to configure the pipeline, which automates the process of building the project, running tests, and publishing test reports.

## Automation Demo

You can watch the automation framework in action by [clicking here](demo/FlightBooking.mov).



