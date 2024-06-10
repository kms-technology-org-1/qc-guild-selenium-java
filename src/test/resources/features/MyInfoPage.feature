@userStory2
Feature: As an ESS user, I am able to update my information in my ESS account.

  Background: Open the browser
    Given user navigate to Login page
    Then page title is "OrangeHRM"
    When user logged in that page by Admin role
    Then user is on '/web/index.php/dashboard/index' page
    Then page title is "OrangeHRM"
    When user creates an ESS account
    Then verify alert message "Successfully Saved" is displayed

  @HappyCases
  Scenario: ESS update the Skills on the Qualifications section in My Info page
    #Admin add new skill
    When user clicks "Admin" item in the main menu
    And user creates "Skills" property with name "HRM_PROPERTY_RANDOM" in the Qualifications successfully
    Then verify alert message "Successfully Saved" is displayed
    And verify the "Skills" record with "HRM_PROPERTY_RANDOM" is displayed in table

    #ESS update the Skills on the Qualifications
    And user logout their account
    And user login with newly created ESS account
    Then user is on '/web/index.php/dashboard/index' page
    And user clicks "My Info" item in the main menu
    And user clicks on tab 'Qualifications'
    And user updates the Skill with name "HRM_PROPERTY_RANDOM"
    And user inputs "HRM_RANDOM_NUMBER" with length 2 into "Years of Experience" field
    And user inputs random string with length 50 into "Comments" textarea field
    And user clicks 'Save' button
    Then verify the "Skills" record with "HRM_PROPERTY_RANDOM" is displayed in table

  @HappyCases
  Scenario: ESS update the Language on the Qualifications section in My Info page
    #Admin add new Language
    When user clicks "Admin" item in the main menu
    And user creates "Languages" property with name "HRM_PROPERTY_RANDOM" in the Qualifications successfully
    Then verify alert message "Successfully Saved" is displayed
    And verify the "Languages" record with "HRM_PROPERTY_RANDOM" is displayed in table

    #ESS update the Language on the Qualifications
    When user logout their account
    And user login with newly created ESS account
    Then user is on '/web/index.php/dashboard/index' page
    And user clicks "My Info" item in the main menu
    And user clicks on tab 'Qualifications'
    And user updates the Language with name "HRM_PROPERTY_RANDOM" and Fluency "Writing" and Competency "Good"
    And user clicks 'Save' button
    Then verify the "Languages" record with "HRM_PROPERTY_RANDOM" is displayed in table

  @ErrorCases
  Scenario Outline: Verify validation fields in the Skills section under Qualifications
    # Admin add new skill
    When user clicks "Admin" item in the main menu
    And user creates "Skills" property with name "HRM_PROPERTY_RANDOM" in the Qualifications successfully
    Then verify alert message "Successfully Saved" is displayed
    And verify the "Skills" record with "HRM_PROPERTY_RANDOM" is displayed in table

    # ESS update the Skills on the Qualifications
    When user logout their account
    And user login with newly created ESS account
    Then user is on '/web/index.php/dashboard/index' page
    And user clicks "My Info" item in the main menu
    And user clicks on tab 'Qualifications'
    And user updates the Skill with name "<skillValue>"
    And user inputs "<randomCharacter>" with length <length> into "Years of Experience" field
    And user inputs random string with length <length> into "Comments" textarea field
    And user clicks 'Save' button
    Then verify a error message "<message>" is shown under "<fieldName>" field

    Examples:
      | TC | testCases                                            | skillValue          | randomCharacter   | length | fieldName           | message                          |
      | 03 | non-select the skill value in the dropdown           |                     | HRM_RANDOM_NUMBER | 2      | Skill               | Required                         |
      | 04 | Years of Experience with a number greater than 100   | HRM_PROPERTY_RANDOM | HRM_RANDOM_NUMBER | 3      | Years of Experience | Should be less than 100          |
      | 05 | Years of Experience contains non-number              | HRM_PROPERTY_RANDOM | HRM_RANDOM_STRING | 2      | Years of Experience | Should be a number               |
      | 06 | a comments with a length greater than 100 characters | HRM_PROPERTY_RANDOM | HRM_RANDOM_STRING | 101    | Comments            | Should not exceed 100 characters |

  @ErrorCases:
  Scenario Outline: Verify validation fields in the Languages section under Qualifications
    # Admin add new Language
    When user clicks "Admin" item in the main menu
    And user creates "Languages" property with name "HRM_PROPERTY_RANDOM" in the Qualifications successfully
    Then verify alert message "Successfully Saved" is displayed
    And verify the "Languages" record with "HRM_PROPERTY_RANDOM" is displayed in table

    # ESS update the Languages on the Qualifications
    When user logout their account
    And user login with newly created ESS account
    Then user is on '/web/index.php/dashboard/index' page
    And user clicks "My Info" item in the main menu
    And user clicks on tab 'Qualifications'
    And user updates the Language with name "<languageValue>" and Fluency "<fluencyValue>" and Competency "<competencyValue>"
    And user inputs random string with length <length> into "Comments" textarea field
    And user clicks 'Save' button
    Then verify a error message "<message>" is shown under "<fieldName>" field

    Examples:
      | TC | testCases                                            | languageValue       | fluencyValue | competencyValue | length | fieldName  | message                          |
      | 07 | non-select the language value                        |                     |              | Good            | 2      | Language   | Required                         |
      | 08 | non-select the fluency value                         | HRM_PROPERTY_RANDOM |              | Basic           | 7      | Fluency    | Required                         |
      | 09 | non-select the competency value                      | HRM_PROPERTY_RANDOM | Writing      |                 | 99     | Competency | Required                         |
      | 10 | a comments with a length greater than 100 characters | HRM_PROPERTY_RANDOM | Speaking     | Good            | 101    | Comments   | Should not exceed 100 characters |