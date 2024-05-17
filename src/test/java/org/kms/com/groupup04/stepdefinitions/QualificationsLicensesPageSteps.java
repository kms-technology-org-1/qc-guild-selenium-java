package org.kms.com.groupup04.stepdefinitions;

import io.cucumber.java.en.Then;
import org.kms.com.groupup04.pageobjects.BasePage;
import org.kms.com.groupup04.pageobjects.QualificationsLicensesPage;
import org.openqa.selenium.support.PageFactory;

public class QualificationsLicensesPageSteps extends BasePage {
    QualificationsLicensesPage qualificationsLicensesPage = PageFactory.initElements(driver, QualificationsLicensesPage.class);

    @Then("user adds new license with {string} successfully")
    public void userAddsNewLicenseWithSuccessfully(String licenseName) {
        qualificationsLicensesPage.addNewLicenses(licenseName);
    }
}
