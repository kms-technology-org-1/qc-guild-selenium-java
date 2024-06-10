@login
Feature: Login to OrangeHRM System
    As a user
    I want to login to OrangeHRM System
    So that I can access the system

  Scenario: Login with valid credentials
    Given user navigate to Login page
    Then page title is "OrangeHRM"
    When user logged in that page by Admin role
    Then user is on '/web/index.php/dashboard/index' page