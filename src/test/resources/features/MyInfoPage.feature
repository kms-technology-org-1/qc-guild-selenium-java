@userStory2
Feature: As an ESS user, I am able to update my information in my ESS account.

  Background: Open the browser
    Given user navigate to Login page
    Then page title is "OrangeHRM"
    When user logged in that page by Admin role
    Then page title is "OrangeHRM"
    When user creates an ESS account

  @HappyCases
  Scenario: ESS update the Skills on the Qualifications section in My Info page
    #Admin add new skill
    When user clicks "Admin" item in the main menu
    And user creates "Skills" property with name "HRM_PROPERTY_RANDOM" in the Qualifications successfully

    #ESS update the Skills on the Qualifications
    And user logout their account
    And user login with newly created ESS account
    And user clicks "My Info" item in the main menu
    And user clicks on tab 'Qualifications'
    And user updates the Skill with name "HRM_PROPERTY_RANDOM"
    And user inputs "HRM_RANDOM_NUMBER" with length 2 into "Years of Experience" field
    And user inputs random string with length 50 into "Comments" textarea field
    And user clicks 'Save' button
    Then verify the "Skills" with "HRM_PROPERTY_RANDOM" is displayed in table

    # Clean the test data: ESS account
    When user logout their account
    And user logged in that page by Admin role
    And user clicks "Admin" item in the main menu
    And user deletes a record containing newly created ESS user to clean testing environment
    And user clicks the "Yes, Delete" button
    # Clean the test data: Skill
    When user clicks "Admin" item in the main menu
    And user clicks "Qualifications" dropdown and select "Skills" item in top-bar menu
    And user deletes the record "Skills" with "HRM_PROPERTY_RANDOM" to clean testing environment
    And user clicks the "Yes, Delete" button

  @HappyCases
  Scenario: ESS update the Language on the Qualifications section in My Info page
    #Admin add new Language
    When user clicks "Admin" item in the main menu
    And user creates "Languages" property with name "HRM_PROPERTY_RANDOM" in the Qualifications successfully

    #ESS update the Language on the Qualifications
    When user logout their account
    And user login with newly created ESS account
    And user clicks "My Info" item in the main menu
    And user clicks on tab 'Qualifications'
    And user updates the Language with name "HRM_PROPERTY_RANDOM" and Fluency "Writing" and Competency "Good"
    And user clicks 'Save' button
    Then verify the "Languages" with "HRM_PROPERTY_RANDOM" is displayed in table

    #Clean the test data: ESS account
    When user logout their account
    And user logged in that page by Admin role
    And user clicks "Admin" item in the main menu
    And user deletes a record containing newly created ESS user to clean testing environment
    And user clicks the "Yes, Delete" button

    # Clean the test data: Language
    When user clicks "Qualifications" dropdown and select "Languages" item in top-bar menu
    And user deletes the record "Languages" with "HRM_PROPERTY_RANDOM" to clean testing environment
    And user clicks the "Yes, Delete" button

  @ErrorCases
  Scenario Outline: Verify validation fields in the Skills section under Qualifications
    # Admin add new skill
    When user clicks "Admin" item in the main menu
    And user creates "Skills" property with name "HRM_PROPERTY_RANDOM" in the Qualifications successfully

    # ESS update the Skills on the Qualifications
    When user logout their account
    And user login with newly created ESS account
    And user clicks "My Info" item in the main menu
    And user clicks on tab 'Qualifications'
    And user updates the Skill with name "<skillValue>"
    And user inputs "<randomCharacter>" with length <length> into "Years of Experience" field
    And user inputs random string with length <length> into "Comments" textarea field
    And user clicks 'Save' button
    Then verify a error message "<message>" is shown under "<fieldName>" field

    #Clean the test data: ESS account
    When user logout their account
    And user logged in that page by Admin role
    And user clicks "Admin" item in the main menu
    And user deletes a record containing newly created ESS user to clean testing environment
    And user clicks the "Yes, Delete" button

    #Clean the test data: Skill
    When user clicks "Admin" item in the main menu
    And user clicks "Qualifications" dropdown and select "Skills" item in top-bar menu
    And user deletes the record "Skills" with "HRM_PROPERTY_RANDOM" to clean testing environment
    And user clicks the "Yes, Delete" button

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

    # ESS update the Languages on the Qualifications
    When user logout their account
    And user login with newly created ESS account
    And user clicks "My Info" item in the main menu
    And user clicks on tab 'Qualifications'
    And user updates the Language with name "<languageValue>" and Fluency "<fluencyValue>" and Competency "<competencyValue>"
    And user inputs random string with length <length> into "Comments" textarea field
    And user clicks 'Save' button
    Then verify a error message "<message>" is shown under "<fieldName>" field

    # Clean the test data: ESS account
    When user logout their account
    And user logged in that page by Admin role
    And user clicks "Admin" item in the main menu
    And user deletes a record containing newly created ESS user to clean testing environment
    And user clicks the "Yes, Delete" button
    # Clean the test data: Languages
    When user clicks "Qualifications" dropdown and select "Languages" item in top-bar menu
    And user deletes the record "Languages" with "HRM_PROPERTY_RANDOM" to clean testing environment
    And user clicks the "Yes, Delete" button

    Examples:
      | TC | testCases                                            | languageValue       | fluencyValue | competencyValue | length | fieldName  | message                          |
      | 07 | non-select the language value                        |                     |              | Mother Tongue   | 2      | Language   | Required                         |
      | 08 | non-select the fluency value                         | HRM_PROPERTY_RANDOM |              | Basic           | 7      | Fluency    | Required                         |
      | 09 | non-select the competency value                      | HRM_PROPERTY_RANDOM | Writing      |                 | 99     | Competency | Required                         |
      | 10 | a comments with a length greater than 100 characters | HRM_PROPERTY_RANDOM | Speaking     | Good            | 101    | Comments   | Should not exceed 100 characters |