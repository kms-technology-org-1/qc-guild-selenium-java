package org.kms.com.groupup04.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.kms.com.groupup04.pageobjects.CommonPage;
import org.testng.Assert;

public class CommonPageSteps extends CommonPage {
//    CommonPage commonPage = PageFactory.initElements(driver, CommonPage.class);

    @Then("page title is {string}")
    public void verifyPageTitle(String expectedTitle) {
        verifyThePageTitle(expectedTitle);
    }

    @Then("verify the Page header is {string}")
    public void verifyThePageHeaderIsDashboard(String expectedHeader) {
        verifyPageHeader(expectedHeader);
    }

    @When("a user is on {string} page")
    public void userIsOnSpecificPage(String page) {
        Assert.assertEquals(driver.getCurrentUrl(), config.getProperty("base_url") + page);
    }

    @Then("verify the main title {string} is displayed correctly")
    public void verifyMainTitleIsDisplayed(String expectedMainTitle) {
        verifyMainTitle(expectedMainTitle);
    }

    @Then("verify a error message {string} is shown under {string} field")
    public void verifyValidationErrorMsg(String msg, String fieldName) {
        sleepInSecond(1);
        verifyValidationErrorMessage(msg, fieldName);
    }

    @When("user clicks {string} item in the main menu")
    public void clickToMainMenu(String itemName) {
        clickToMainMenuItem(itemName);
        waitForLoadingIndicatorToDisappear(driver, eLoadingIndicator);
    }

    @When("user clicks {string} item in top-bar menu")
    public void selectDropdownMenuItemByText(String itemName) {
        clickToTopBarMenuItem(itemName);
        sleepInSecond(5);
    }

    @When("user clicks {string} dropdown and select {string} item in top-bar menu")
    public void selectDropdownMenuItemByTxt(String dropdownName, String item) {
        selectDropdownMenuItemByText(dropdownName, item);
        waitForLoadingIndicatorToDisappear(driver, eLoadingIndicator);
    }

    @When("user clicks {string} button")
    public void userClicksButton(String btnName) {
        waitForLoadingIndicatorToDisappear(driver, eLoadingIndicator);
        clickToDynamicButtonByName(driver, btnName);
    }

    @Then("verify the {string} is displayed in table")
    public void verifyRecordWithTitleIsDisplayed(String titleName) {
        verifyRecordWithTitleNameIsDisplayed(titleName);
    }

    @When("user delete a record containing newly created ESS user to clean testing environment")
    public void deleteUserByUserName() {
        deleteNewlyCreatedESSUser();
    }

    @When("user clicks the {string} button")
    public void clickButtonByName(String btnName) {
        clickToDynamicButtonByName(driver, btnName);
        sleepInSecond(5);
    }

    @When("user delete the record {string} to clean testing environment")
    public void deleteDynamicRecordToCleanEnv(String recordName) {
        deleteRecordToCleanEnv(recordName);
    }
}