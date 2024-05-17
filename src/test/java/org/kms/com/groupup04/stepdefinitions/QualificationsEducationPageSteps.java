package org.kms.com.groupup04.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.kms.com.groupup04.pageobjects.BasePage;
import org.kms.com.groupup04.pageobjects.QualificationsEducationPage;
import org.openqa.selenium.support.PageFactory;

public class QualificationsEducationPageSteps extends BasePage {
    QualificationsEducationPage qualificationsEducationPage = PageFactory.initElements(driver, QualificationsEducationPage.class);

    @When("user adds new education with {string} successfully")
    public void addNewEducation(String educationName) {
        qualificationsEducationPage.addNewEducation(educationName);
    }

}
