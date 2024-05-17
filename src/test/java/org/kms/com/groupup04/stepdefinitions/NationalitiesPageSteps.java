package org.kms.com.groupup04.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.kms.com.groupup04.pageobjects.BasePage;
import org.kms.com.groupup04.pageobjects.NationalitiesPage;
import org.openqa.selenium.support.PageFactory;

public class NationalitiesPageSteps extends BasePage {
    NationalitiesPage nationalitiesPage = PageFactory.initElements(driver, NationalitiesPage.class);

    @And("user adds new nationality with {string} successfully")
    public void addNewNationality(String newNationality) {
        nationalitiesPage.addNewNationality(newNationality);
    }

    @Then("verify the {string} nationality is displayed in table")
    public void verifyNameExistsOrNotInTable(String nationalityOption) {
        nationalitiesPage.verifyNameExistsOrNotInTable(nationalityOption, true);
    }

    @And("user delete the record nationality {string} to clean testing environment")
    public void deleteNationalityRecord1(String nationality) {
        nationalitiesPage.deleteNationalityRecord(nationality);
    }
}
