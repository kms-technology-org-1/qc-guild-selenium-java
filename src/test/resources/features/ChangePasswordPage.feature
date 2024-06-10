@userStory1
Feature: As an ESS user, I can change the password and login to workspace with new password

  Background: Open the browser
    Given user navigate to Login page
    Then page title is "OrangeHRM"
    When user logged in that page by Admin role
    Then user is on '/web/index.php/dashboard/index' page
    Then page title is "OrangeHRM"
    When user creates an ESS account
    Then verify alert message "Successfully Saved" is displayed
    And user logout their account
    And user login with newly created ESS account
    Then user is on '/web/index.php/dashboard/index' page
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
    Then user is on '/web/index.php/dashboard/index' page
    And page title is "OrangeHRM"

    Examples:
      | TC | testCases                                                         | passwordLength | passwordCondition |
      | 01 | a new valid password which has a length greater than 8 characters | 9              | full options      |
      | 02 | a new valid password which has a length of 8 characters           | 8              | full options      |
      | 03 | a new valid password which has a length of 63 characters          | 63             | full options      |
      | 04 | a new valid password which has a length of 64 characters          | 64             | full options      |


  @ErrorCases
  Scenario Outline: <TC>. Verify that the user can not change the password when providing <testCases>
    When generating a new password with the length of <passwordLength> characters and "<passwordCondition>" condition
    And user inputs "<currentPassword>" into "Current Password" field
    And user inputs "<confirmPassword>" into "Confirm Password" field
    And user inputs "<newPassword>" into "Password" field
    And user clicks "Save" button
    Then verify a error message "<message>" is shown under "<fieldName>" field

    Examples:
      | TC | testCases                                                | passwordLength | passwordCondition    | currentPassword       | newPassword                    | confirmPassword                | fieldName        | message                                                                                                                         |
      | 05 | a new password with a length less than 8 characters      | 7              | full options         | HRM_EMPLOYEE_PASSWORD | HRM_EMPLOYEE_PASSWORD_RANDOMLY | HRM_EMPLOYEE_PASSWORD_RANDOMLY | Password         | Should have at least 8 characters                                                                                               |
      | 06 | a new password with a length greater than 64 characters  | 65             | full options         | HRM_EMPLOYEE_PASSWORD | HRM_EMPLOYEE_PASSWORD_RANDOMLY | HRM_EMPLOYEE_PASSWORD_RANDOMLY | Password         | Should not exceed 64 characters                                                                                                 |
      | 07 | a new password with no special character                 | 9              | no special character | HRM_EMPLOYEE_PASSWORD | HRM_EMPLOYEE_PASSWORD_RANDOMLY | HRM_EMPLOYEE_PASSWORD_RANDOMLY | Password         | Your password must contain a lower-case letter, an upper-case letter, a digit and a special character. Try a different password |
      | 08 | a new password with no lower-case letter                 | 9              | no lower-case letter | HRM_EMPLOYEE_PASSWORD | HRM_EMPLOYEE_PASSWORD_RANDOMLY | HRM_EMPLOYEE_PASSWORD_RANDOMLY | Password         | Your password must contain a lower-case letter, an upper-case letter, a digit and a special character. Try a different password |
      | 09 | a new password with no upper-case letter                 | 9              | no upper-case letter | HRM_EMPLOYEE_PASSWORD | HRM_EMPLOYEE_PASSWORD_RANDOMLY | HRM_EMPLOYEE_PASSWORD_RANDOMLY | Password         | Your password must contain a lower-case letter, an upper-case letter, a digit and a special character. Try a different password |
      | 10 | a new password with no digit                             | 9              | no digit             | HRM_EMPLOYEE_PASSWORD | HRM_EMPLOYEE_PASSWORD_RANDOMLY | HRM_EMPLOYEE_PASSWORD_RANDOMLY | Password         | Your password must contain a lower-case letter, an upper-case letter, a digit and a special character. Try a different password |
      | 11 | a new password with spaces                               | 9              | contain space        | HRM_EMPLOYEE_PASSWORD | HRM_EMPLOYEE_PASSWORD_RANDOMLY | HRM_EMPLOYEE_PASSWORD_RANDOMLY | Password         | Your password should not contain spaces.                                                                                        |
      | 12 | the current password is empty                            | 9              | full options         |                       | HRM_EMPLOYEE_PASSWORD_RANDOMLY | HRM_EMPLOYEE_PASSWORD_RANDOMLY | Current Password | Required                                                                                                                        |
      | 13 | the new password is empty                                | 9              | full options         | HRM_EMPLOYEE_PASSWORD |                                | HRM_EMPLOYEE_PASSWORD_RANDOMLY | Password         | Required                                                                                                                        |
      | 14 | the confirm password is empty                            | 9              | full options         | HRM_EMPLOYEE_PASSWORD | HRM_EMPLOYEE_PASSWORD_RANDOMLY |                                | Confirm Password | Required                                                                                                                        |
      | 15 | a mismatch between the new password and confirm password | 9              | full options         | HRM_EMPLOYEE_PASSWORD | HRM_EMPLOYEE_PASSWORD_RANDOMLY | HRM_EMPLOYEE_PASSWORD          | Confirm Password | Passwords do not match                                                                                                          |

  @ErrorCases
  Scenario: 16. Verify that the user can not change the password when providing wrong the current password
    When generating a new password with the length of 9 characters and "full options" condition
    And user inputs "HRM_EMPLOYEE_PASSWORD_RANDOMLY" into "Current Password" field
    And user inputs "HRM_EMPLOYEE_PASSWORD_RANDOMLY" into "Password" field
    And user inputs "HRM_EMPLOYEE_PASSWORD_RANDOMLY" into "Confirm Password" field
    And user clicks "Save" button
    Then verify the main title "Update Password" is displayed correctly
    And verify alert message "Invalid Parameter" is displayed