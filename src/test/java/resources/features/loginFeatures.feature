Feature: Login to application

  Scenario: Logging in to the application
    Given user navigates to login page by opening Chrome
    When user enters correct <username> AND <password> values
    Then user is directed to the homepage

