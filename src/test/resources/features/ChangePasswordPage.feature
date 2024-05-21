@userStory1
Feature: As an ESS user, I can change the password and login to workspace with new password

  Background: Open the browser
    Given user navigate to Login page
    Then page title is "OrangeHRM"
    When user logged in that page by Admin role
    Then page title is "OrangeHRM"
    When user creates an ESS account
    And user logout their account
    And user login with newly created ESS account
    And user clicks on Change Password in User Profile
    Then verify the main title "Update Password" is displayed correctly

  @HappyCases
  Scenario Outline: <TC>. Verify that the user can change the password successfully when providing <testCases>
    When generating a new password with the length of <passwordLength> characters and "<passwordCondition>" condition
    And user inputs "HRM_EMPLOYEE_PASSWORD" into "Current Password" field
    And user inputs "HRM_EMPLOYEE_PASSWORD_RANDOMLY" into "Password" field
    And user inputs "HRM_EMPLOYEE_PASSWORD_RANDOMLY" into "Confirm Password" field
    And user clicks "Save" button
    Then verify alert message "Successfully Saved" is displayed
    When user logout their account
    And user login with new password
    Then page title is "OrangeHRM"
      # Clean the test data: ESS account
    When user logout their account
    And user logged in that page by Admin role
    And user clicks "Admin" item in the main menu
    And user deletes a record containing newly created ESS user to clean testing environment
    And user clicks the "Yes, Delete" button

    Examples:
      | TC | testCases                                                         | passwordLength | passwordCondition |
      | 01 | a new valid password which has a length greater than 8 characters | 9              | full options      |
      | 02 | a new valid password which has a length of 8 characters           | 8              | full options      |
      | 03 | a new valid password which has a length of 63 characters          | 63             | full options      |
      | 04 | a new valid password which has a length of 64 characters          | 64             | full options      |


  @ErrorCases
  Scenario Outline: <TC>. Verify that the user can not change the password when providing <testCases>
    When generating a new password with the length of <passwordLength> characters and "<passwordCondition>" condition
    And user inputs "HRM_EMPLOYEE_PASSWORD" into "Current Password" field
    And user inputs "HRM_EMPLOYEE_PASSWORD_RANDOMLY" into "Password" field
    And user inputs "HRM_EMPLOYEE_PASSWORD_RANDOMLY" into "Confirm Password" field
    And user clicks "Save" button
    Then verify a error message "<message>" is shown under "Password" field
      # Clean the test data: ESS account
    When user logout their account
    And user logged in that page by Admin role
    And user clicks "Admin" item in the main menu
    And user deletes a record containing newly created ESS user to clean testing environment
    And user clicks the "Yes, Delete" button

    Examples:
      | TC | testCases                                               | passwordLength | passwordCondition | message                           |
      | 05 | a new password with a length less than 8 characters     | 7              | full options      | Should have at least 8 characters |
      | 06 | a new password with a length greater than 64 characters | 65             | full options      | Should not exceed 64 characters   |


  @ErrorCases
  Scenario Outline: <TC>. Verify that the user can not change the password when providing <testCases>
    When generating a new password with the length of 9 characters and "<passwordCondition>" condition
    And user inputs "HRM_EMPLOYEE_PASSWORD" into "Current Password" field
    And user inputs "HRM_EMPLOYEE_PASSWORD_RANDOMLY" into "Password" field
    And user inputs "HRM_EMPLOYEE_PASSWORD_RANDOMLY" into "Confirm Password" field
    And user clicks "Save" button
    Then verify a error message "<message>" is shown under "Password" field
      # Clean the test data: ESS account
    When user logout their account
    And user logged in that page by Admin role
    And user clicks "Admin" item in the main menu
    And user deletes a record containing newly created ESS user to clean testing environment
    And user clicks the "Yes, Delete" button

    Examples:
      | TC | testCases                                | passwordCondition    | message                                                                                                                         |
      | 07 | a new password with no special character | no special character | Your password must contain a lower-case letter, an upper-case letter, a digit and a special character. Try a different password |
      | 08 | a new password with no lower-case letter | no lower-case letter | Your password must contain a lower-case letter, an upper-case letter, a digit and a special character. Try a different password |
      | 09 | a new password with no upper-case letter | no upper-case letter | Your password must contain a lower-case letter, an upper-case letter, a digit and a special character. Try a different password |
      | 10 | a new password with no digit             | no digit             | Your password must contain a lower-case letter, an upper-case letter, a digit and a special character. Try a different password |
      | 11 | a new password with spaces               | contain space        | Your password should not contain spaces.                                                                                        |


  @ErrorCases
  Scenario: 12. Verify that the user can not change the password when providing wrong the current password
    When generating a new password with the length of 9 characters and "full options" condition
    And user inputs "HRM_EMPLOYEE_PASSWORD_RANDOMLY" into "Current Password" field
    And user inputs "HRM_EMPLOYEE_PASSWORD_RANDOMLY" into "Password" field
    And user inputs "HRM_EMPLOYEE_PASSWORD_RANDOMLY" into "Confirm Password" field
    And user clicks "Save" button
    Then verify the main title "Update Password" is displayed correctly
    And verify alert message "Invalid Parameter" is displayed
    # Clean the test data: ESS account
    When user logout their account
    And user logged in that page by Admin role
    And user clicks "Admin" item in the main menu
    And user deletes a record containing newly created ESS user to clean testing environment
    And user clicks the "Yes, Delete" button

  @ErrorCases
  Scenario Outline: <TC>. Verify that the user can not change the password when providing <testCases>
    When generating a new password with the length of 9 characters and "full options" condition
    And user inputs "<currentPassword>" into "Current Password" field
    And user inputs "<confirmPassword>" into "Confirm Password" field
    And user inputs "<newPassword>" into "Password" field
    And user clicks "Save" button
    Then verify the main title 'Update Password' is displayed correctly
    Then verify a error message "<message>" is shown under "<fieldName>" field
    # Clean the test data: ESS account
    When user logout their account
    And user logged in that page by Admin role
    And user clicks "Admin" item in the main menu
    And user deletes a record containing newly created ESS user to clean testing environment
    And user clicks the "Yes, Delete" button

    Examples:
      | TC | testCases                                                | currentPassword       | newPassword                    | confirmPassword                | fieldName        | message                |
      | 13 | the current password is empty                            |                       | HRM_EMPLOYEE_PASSWORD_RANDOMLY | HRM_EMPLOYEE_PASSWORD_RANDOMLY | Current Password | Required               |
      | 14 | the new password is empty                                | HRM_EMPLOYEE_PASSWORD |                                | HRM_EMPLOYEE_PASSWORD_RANDOMLY | Password         | Required               |
      | 15 | the confirm password is empty                            | HRM_EMPLOYEE_PASSWORD | HRM_EMPLOYEE_PASSWORD_RANDOMLY |                                | Confirm Password | Required               |
      | 16 | a mismatch between the new password and confirm password | HRM_EMPLOYEE_PASSWORD | HRM_EMPLOYEE_PASSWORD_RANDOMLY | HRM_EMPLOYEE_PASSWORD          | Confirm Password | Passwords do not match |