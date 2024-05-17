package org.kms.com.groupup04.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.kms.com.groupup04.pageobjects.*;
import org.openqa.selenium.support.PageFactory;

import java.io.FileNotFoundException;

public class ChangePasswordPageSteps extends BasePage {

    ChangePasswordPage changePasswordPage = PageFactory.initElements(driver, ChangePasswordPage.class);
    AddEmployeePage addEmployeePage = PageFactory.initElements(driver, AddEmployeePage.class);

    @When("user create an ESS account")
    public void createESSAccount() throws FileNotFoundException {
        addEmployeePage.goToAddEmployeePage();
        addEmployeePage.createNewEmployee();
//        addEmployeePage.verifyAlertSaveSuccessfully();
    }

    @And("user click on Change Password in User Profile")
    public void clickOnchangePasswordInUserProfile() {
        changePasswordPage.clickOnchangePasswordInUserProfile();
    }

    @When("generating a new password with the length of {} characters")
    public void generatingANewPasswordWithTheLengthOfCharacters(int length) {
        changePasswordPage.generatePasswordWithLength(length);
    }

    @And("user inputs {string} into {string} field")
    public void inputValueIntoField(String value, String fieldName) {
        changePasswordPage.inputValueIntoField(value, fieldName);
    }

    @Then("verify alert message {string} is displayed")
    public void verifyAlertSaveSuccessfully(String msg) {
        changePasswordPage.verifyAlertMsg(msg);
    }

    @When("generating a new password with the length of {} characters and {} condition")
    public void generatingANewPasswordWithTheLengthOfCharactersAndCondition(int  length, String condition) {
        changePasswordPage.generatePwdWithLengthAndCondition(length, condition);
    }
}
