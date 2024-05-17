package org.kms.com.groupup04.stepdefinitions;

import io.cucumber.java.en.Then;
import org.kms.com.groupup04.pageobjects.BasePage;
import org.kms.com.groupup04.pageobjects.QualificationsEducationPage;
import org.kms.com.groupup04.pageobjects.QualificationsSkillsPage;
import org.openqa.selenium.support.PageFactory;

public class QualificationsSkillsPageSteps extends BasePage {
    QualificationsSkillsPage qualificationsSkillsPage = PageFactory.initElements(driver, QualificationsSkillsPage.class);

    @Then("user adds new skill with {string} and {string} successfully")
    public void userAddsNewSkillWithAndSuccessfully(String skillName, String skillDescription) {
        qualificationsSkillsPage.addNewSkill(skillName, skillDescription);
    }
}
