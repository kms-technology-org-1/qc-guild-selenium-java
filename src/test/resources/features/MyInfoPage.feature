@userStory2
Feature: As an ESS user, I am able to update my information in my ESS account.

  Scenario: : Open the browser
    Given user navigate to Login page
    Then page title is "OrangeHRM"
    When user logged in that page by Admin role
    Then page title is "OrangeHRM"
    When user create an ESS account

        #Admin add the new Nationality
    And user clicks "Admin" item in the main menu
    And user clicks "Nationalities" item in top-bar menu
    And user adds new nationality with "a Nationality" successfully
    Then verify the "a Nationality" nationality is displayed in table
      # Admin Add the new Education
    When user clicks "Qualifications" dropdown and select "Education" item in top-bar menu
    And user adds new education with "a Education" successfully
    Then verify the "a Education" is displayed in table
      # Admin Add the new skill
    When user clicks "Qualifications" dropdown and select "Skills" item in top-bar menu
    And user adds new skill with "a Skill" and "Testing skill description" successfully
    Then verify the "a Skill" is displayed in table
      # Admin Add the new languages
    When user clicks "Qualifications" dropdown and select "Languages" item in top-bar menu
    And user adds new language with "a Language" successfully
    Then verify the "a Language" is displayed in table
      # Admin Add the new licenses
    When user clicks "Qualifications" dropdown and select "Licenses" item in top-bar menu
    And user adds new license with "a License" successfully
    Then verify the "a License" is displayed in table

        #ESS update the Nationality on the Personal Detail
    When user logout their account
    And user login with newly created ESS account
    And user clicks "My Info" item in the main menu
    And user update the nationality "a Nationality" on the Personal detail
    And user clicks "Save" button
      # ESS update the Education, Skill, Languages, Licenses on the Qualifications
    When user clicks on tab 'Qualifications'
    And user update the education "a Education" on Qualifications
    And user clicks 'Save' button
    Then verify the "a Education" is displayed in table

    When user update the skill "a Skill" on Qualifications
    And user clicks 'Save' button
    Then verify the "a Skill" is displayed in table

    When user update the language "a Language" with Fluency "Writing" and Competency "Good" on Qualifications
    And user clicks 'Save' button
    Then verify the "a Language" is displayed in table

    When user update the licenses "a License" on Qualifications
    And user clicks 'Save' button
    Then verify the "a License" is displayed in table

        # Clean the test data: ESS account
    When user logout their account
    And user logged in that page by Admin role
    And user clicks "Admin" item in the main menu
    And user delete a record containing newly created ESS user to clean testing environment
    And user clicks the "Yes, Delete" button
      # Clean the test data: Nationality
    And user clicks "Nationalities" item in top-bar menu
    And user delete the record nationality "a Nationality" to clean testing environment
    And user clicks the "Yes, Delete" button
      # Clean the test data: Education
    When user clicks "Qualifications" dropdown and select "Education" item in top-bar menu
    And user delete the record "a Education" to clean testing environment
    And user clicks the "Yes, Delete" button
      # Clean the test data: Skill
    When user clicks "Qualifications" dropdown and select "Skills" item in top-bar menu
    And user delete the record "a Skill" to clean testing environment
    And user clicks the "Yes, Delete" button
      # Clean the test data: Language
    When user clicks "Qualifications" dropdown and select "Languages" item in top-bar menu
    And user delete the record "a Language" to clean testing environment
    And user clicks the "Yes, Delete" button
      # Clean the test data: License
    When user clicks "Qualifications" dropdown and select "Licenses" item in top-bar menu
    And user delete the record "a License" to clean testing environment
    And user clicks the "Yes, Delete" button

