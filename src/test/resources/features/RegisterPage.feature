@register
Feature: Register account in AbanteCart Application

  Scenario: Register account with valid data
    Given user navigate to Register page
    Then page title is "Create Account"