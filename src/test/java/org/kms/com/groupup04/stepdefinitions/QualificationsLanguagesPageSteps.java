package org.kms.com.groupup04.stepdefinitions;

import io.cucumber.java.en.Then;
import org.kms.com.groupup04.pageobjects.BasePage;
import org.kms.com.groupup04.pageobjects.QualificationsEducationPage;
import org.kms.com.groupup04.pageobjects.QualificationsLanguagesPage;
import org.openqa.selenium.support.PageFactory;

public class QualificationsLanguagesPageSteps extends BasePage {
    QualificationsLanguagesPage qualificationsLanguagesPage = PageFactory.initElements(driver, QualificationsLanguagesPage.class);

    @Then("user adds new language with {string} successfully")
    public void userAddsNewLanguageWithSuccessfully(String languageName) {
        qualificationsLanguagesPage.addNewLanguages(languageName);
    }
}
