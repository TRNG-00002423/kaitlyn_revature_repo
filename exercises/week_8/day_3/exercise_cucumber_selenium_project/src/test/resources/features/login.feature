@login
Feature: User Authentication
  As a registered user of the application
  I want to be able to log in to my account
  So that I can access my personalized content

  Background:
    Given the user is on the login page

  @smoke @positive
  Scenario: Successful login with valid credentials
    When the user enters username "tomsmith"
    And the user enters password "SuperSecretPassword!"
    And the user clicks the login button
    Then the user should be redirected to the secure area
    And the user should see a success message containing "You logged into a secure area!"

  @negative
  Scenario: Failed login with invalid password
    When the user enters username "tomsmith"
    And the user enters password "wrongpassword"
    And the user clicks the login button
    Then the user should remain on the login page
    And the user should see an error message containing "Your password is invalid!"

  @negative
  Scenario: Failed login with invalid username
    When the user enters username "wrongusername"
    And the user enters password "SuperSecretPassword!"
    And the user clicks the login button
    Then the user should remain on the login page
    And the user should see an error message containing "Your username is invalid!"

  @negative
  Scenario: Failed login with empty credentials
    When the user enters username ""
    And the user enters password ""
    And the user clicks the login button
    Then the user should remain on the login page
    And the user should see an error message containing "Your username is invalid!"