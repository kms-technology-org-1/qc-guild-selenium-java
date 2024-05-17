@test
Feature: As an ESS user, I can change the password and login to workspace with new password

  Scenario: Verify that the user can not change the password when providing empty Current Password
    Given user navigate to Login page
    When user logged in that page by Admin role
    When user create an ESS account
    And user logout their account
    And user login with newly created ESS account
    And user click on Change Password in User Profile
    Then verify the main title 'Update Password' is displayed correctly
    When generating a new password with the length of 9 characters
    When user inputs "empty" into "Current Password" field
    And user inputs "new password" into "Password" field
    And user inputs "new password" into "Confirm Password" field
    And user clicks 'Save' button
    Then verify a error message 'Required' is shown under 'Current Password' field

            # Clean the test data: ESS account
    When user logout their account
    And user logged in that page by Admin role
    And user clicks "Admin" item in the main menu
    And user delete a record containing newly created ESS user to clean testing environment
    And user clicks the "Yes, Delete" button


