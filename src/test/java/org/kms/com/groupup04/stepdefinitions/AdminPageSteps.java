package org.kms.com.groupup04.stepdefinitions;

import io.cucumber.java.en.And;
import org.kms.com.groupup04.commons.CustomException;
import org.kms.com.groupup04.pageobjects.AdminPage;
import org.kms.com.groupup04.pageobjects.BasePage;
import org.openqa.selenium.support.PageFactory;

public class AdminPageSteps extends BasePage {

    AdminPage adminPage = PageFactory.initElements(driver, AdminPage.class);

    @And("user creates {string} property with name {string} in the Qualifications successfully")
    public void addPropertyInQualifications(String propertyName, String value) throws CustomException {
        adminPage.addNewPropertyInItemNameUnderQualifications(propertyName, value);
    }

    @And("{string} record with {string} is displayed in table")
    public void recordWithIsDisplayedInTable(String propertyName, String propertyValue) throws CustomException {
        adminPage.verifyRecordWithTitleNameIsDisplayed(propertyName, propertyValue);
    }
}