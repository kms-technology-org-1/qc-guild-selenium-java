@login
Feature: Login to AbanteCart Application

  Scenario: Login with valid credentials
    Given user navigate to Login page
    Then page title is "Account Login"