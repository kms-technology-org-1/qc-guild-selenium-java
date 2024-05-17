@test
Feature: As an ESS user, I can change the password and login to workspace with new password

  Background: Open the browser
    Given user navigate to Login page
    Then page title is "OrangeHRM"
    When user logged in that page by Admin role
    Then page title is "OrangeHRM"
    When user create an ESS account
    And user logout their account
    And user login with newly created ESS account
    And user click on Change Password in User Profile
    Then verify the main title 'Update Password' is displayed correctly

  @HappyCases
  Scenario Outline: <TC>. Verify that the user can change the password successfully when providing <testCases>
    When generating a new password with the length of <passwordLength> characters
    And user inputs "current password" into "Current Password" field
    And user inputs "new password" into "Password" field
    And user inputs "new password" into "Confirm Password" field
    And user clicks "Save" button
    Then verify alert message 'Successfully Saved' is displayed
    When user logout their account
    And user login with new password
    And user logout their account
    Examples:
      | TC | testCases                                                         | passwordLength |
      | 01 | a new valid password which has a length greater than 8 characters | 9              |
      | 02 | a new valid password which has a length of 8 characters           | 8              |
      | 03 | a new valid password which has a length of 63 characters          | 63             |
      | 04 | a new valid password which has a length of 64 characters          | 64             |

  @ErrorCases
  Scenario Outline: <TC>. Verify that the user can not change the password when providing <testCases>
    When generating a new password with the length of <passwordLength> characters
    And user inputs "current password" into "Current Password" field
    And user inputs "new password" into "Password" field
    And user inputs "new password" into "Confirm Password" field
    And user clicks "Save" button
    Then verify a error message <message> is shown under "Password" field
    When user logout their account

    Examples:
      | TC | testCases                                               | passwordLength | message                           |
      | 05 | a new password with a length less than 8 characters     | 7              | Should have at least 8 characters |
      | 06 | a new password with a length greater than 64 characters | 65             | Should not exceed 64 characters   |

  @ErrorCases
  Scenario Outline: <TC>. Verify that the user can not change the password when providing <testCases>
    When generating a new password with the length of <passwordLength> characters and <passwordCondition> condition
    When user inputs "current password" into "Current Password" field
    And user inputs "new password" into "Password" field
    And user inputs "new password" into "Confirm Password" field
    And user clicks 'Save' button
    Then verify a error message <message> is shown under 'Password' field
    When user logout their account

    Examples:
      | TC | testCases                                | passwordCondition    | passwordLength | message                                                                                                                         |
      | 07 | a new password with no special character | no special character | 9              | Your password must contain a lower-case letter, an upper-case letter, a digit and a special character. Try a different password |
      | 08 | a new password with no lower-case letter | no lower-case letter | 9              | Your password must contain a lower-case letter, an upper-case letter, a digit and a special character. Try a different password |
      | 09 | a new password with no upper-case letter | no upper-case letter | 9              | Your password must contain a lower-case letter, an upper-case letter, a digit and a special character. Try a different password |
      | 10 | a new password with no digit             | no digit             | 9              | Your password must contain a lower-case letter, an upper-case letter, a digit and a special character. Try a different password |
      | 11 | a new password with spaces               | contain space        | 9              | Your password should not contain spaces.                                                                                        |

  @ErrorCases
  Scenario Outline: Verify that the user can not change the password when providing wrong the current password
    When generating a new password with the length of <passwordLength> characters
    And user inputs "random password" into "Current Password" field
    And user inputs "new password" into "Password" field
    And user inputs "new password" into "Confirm Password" field
    And user clicks "Save" button
    Then verify the main title 'Update Password' is displayed correctly
    And verify alert message '<message>' is displayed
    When user logout their account

    Examples:
      | TC | testCases                               | passwordLength | message           |
      | 12 | user provide wrong the current password | 9              | Invalid Parameter |