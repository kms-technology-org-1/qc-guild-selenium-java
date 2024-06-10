package org.kms.com.groupup04.stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.kms.com.groupup04.commons.CustomException;
import org.kms.com.groupup04.pageobjects.CommonPage;
import org.testng.Assert;

public class CommonPageSteps extends CommonPage {

    @Then("page title is {string}")
    public void verifyPageTitle(String expectedTitle) {
        verifyThePageTitle(expectedTitle);
    }

    @When("a user is on {string} page")
    public void userIsOnSpecificPage(String page) {
        Assert.assertEquals(driver.getCurrentUrl(), config.getProperty("base_url") + page);
    }

    @When("user clicks {string} item in the main menu")
    public void clickToMainMenu(String itemName) {
        clickToMainMenuItem(itemName);
    }

    @When("user clicks the {string} button")
    public void clickButtonByName(String btnName) {
        clickToDynamicButtonByName(driver, btnName);
    }

    @When("user clicks {string} dropdown and select {string} item in top-bar menu")
    public void selectDropdownMenuItemByTxt(String dropdownName, String item) {
        selectDropdownMenuItemByText(dropdownName, item);
    }

    @When("user clicks {string} button")
    public void clickDynamicButton(String btnName) {
        clickToDynamicButtonByName(driver, btnName);
    }

    @Then("verify the {string} record with {string} is displayed in table")
    public void verifyRecordNameIsDisplayedInTable(String propertyName, String propertyValue) throws CustomException {
        verifyRecordWithTitleNameIsDisplayed(propertyName, propertyValue);
    }

    @Then("verify the main title {string} is displayed correctly")
    public void verifyMainTitleIsDisplayed(String expectedMainTitle) {
        verifyMainTitle(expectedMainTitle);
    }

    @Then("verify a error message {string} is shown under {string} field")
    public void verifyValidationErrorMsg(String msg, String fieldName) {
        verifyValidationErrorMessage(msg, fieldName);
    }

    @Then("verify alert message {string} is displayed")
    public void verifyAlertMessage(String msg) {
        verifyAlertMsg(msg);
    }
}