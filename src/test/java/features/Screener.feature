Feature: Screener Login Page

  Background:
    Given User navigates to the Screener Login page

  Scenario: Validate a user is able to login to Screener Page
    Given User is on the Screener Login page
    When User clicks on Login button
    Then User should be able to view the Screener Login page
    And User able to enter valid Username and Password to land to home page or use login using google

  Scenario Outline: Unsuccessful login with invalid or empty credentials
    Given I have entered invalid "<username>" and "<password>"
    When I click on the login button
    Then I should see an error message indicating "<error_message>"

    Examples:
      | username          | password        | error_message                                                                           |
      | invalid@email.com | invalidPassword | Please enter a correct email and password. Note that both fields may be case-sensitive. |