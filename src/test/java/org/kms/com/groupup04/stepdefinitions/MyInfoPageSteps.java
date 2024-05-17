package org.kms.com.groupup04.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.kms.com.groupup04.pageobjects.BasePage;
import org.kms.com.groupup04.pageobjects.MyInfoPage;
import org.openqa.selenium.support.PageFactory;

public class MyInfoPageSteps extends BasePage {
    MyInfoPage myInfoPage = PageFactory.initElements(driver, MyInfoPage.class);

    @When("user clicks on tab {string}")
    public void selectTabName(String tabName) {
        myInfoPage.selectTabName(tabName);
        sleepInSecond(3);
    }

    @And("user update the nationality {string} on the Personal detail")
    public void updateNationalityOnThePersonalDetail(String nationalityName) {
        myInfoPage.updateNationality(nationalityName);
    }

    @And("user update the education {string} on Qualifications")
    public void updateEducationOnQualifications(String educationName) {
        myInfoPage.updateEducation(educationName);
    }

    @And("user update the skill {string} on Qualifications")
    public void updateSkillOnQualifications(String skillName) {
        myInfoPage.updateSkill(skillName);
    }

    @And("user update the language {string} with Fluency {string} and Competency {string} on Qualifications")
    public void updateLanguageOnQualifications(String languageOption, String fluencyOption, String competencyOption) {
        myInfoPage.updateLanguage(languageOption, fluencyOption, competencyOption);
    }

    @And("user update the licenses {string} on Qualifications")
    public void updateLicensesOnQualifications(String licenseName) {
        myInfoPage.updateLicense(licenseName);
    }
}
