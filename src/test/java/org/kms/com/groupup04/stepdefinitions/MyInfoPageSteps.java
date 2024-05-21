package org.kms.com.groupup04.stepdefinitions;

import io.cucumber.java.en.When;
import org.kms.com.groupup04.pageobjects.BasePage;
import org.kms.com.groupup04.pageobjects.MyInfoPage;
import org.openqa.selenium.support.PageFactory;

public class MyInfoPageSteps extends BasePage {

    MyInfoPage myInfoPage = PageFactory.initElements(driver, MyInfoPage.class);

    @When("user clicks on tab {string}")
    public void selectTabName(String tabName) {
        myInfoPage.selectTabName(tabName);
    }

    @When("user inputs random string with length {int} into {string} field")
    public void generateRandomStringWithLength(int length, String fieldName) {
        myInfoPage.generateRandomStringWithLength(length, fieldName);
    }

    @When("user inputs {string} with length {int} into {string} field")
    public void userInputsWithLengthIntoField(String randomCharacter, int length, String fieldName) {
        myInfoPage.generateRandomCharacterWithLength(randomCharacter, length, fieldName);
    }

    @When("user inputs random string with length {int} into {string} textarea field")
    public void generateRandomStringWithLengthInTextArea(int length, String fieldName) {
        myInfoPage.generateRandomStringWithLengthInTextArea(length, fieldName);
    }

    @When("user updates the Skill with name {string}")
    public void updateSkillInQualifications(String skillValue) {
        myInfoPage.updateTheSkillOnQualifications(skillValue);
    }

    @When("user updates the Language with name {string} and Fluency {string} and Competency {string}")
    public void updateLanguageInQualifications(String languageValue, String fluencyValue, String competencyValue) {
        myInfoPage.updateLanguage(languageValue, fluencyValue, competencyValue);
    }
}