package org.kms.com.groupup04.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.kms.com.groupup04.commons.CustomException;
import org.kms.com.groupup04.pageobjects.BasePage;
import org.kms.com.groupup04.pageobjects.ChangePasswordPage;
import org.openqa.selenium.support.PageFactory;

public class ChangePasswordPageSteps extends BasePage {

    ChangePasswordPage changePasswordPage = PageFactory.initElements(driver, ChangePasswordPage.class);

    @And("user clicks on Change Password in User Profile")
    public void clickChangePasswordInUserProfile() {
        changePasswordPage.clickChangePasswordInUserProfile();
    }

    @And("user inputs {string} into {string} field")
    public void inputPasswordIntoField(String value, String fieldName) {
        changePasswordPage.inputPasswordIntoField(value, fieldName);
    }

    @When("generating a new password with the length of {int} characters and {string} condition")
    public void generatingPasswordWithTheLengthOfCharactersAndCondition(int length, String condition) throws CustomException {
        changePasswordPage.generatePwdWithLengthAndCondition(length, condition);
    }
}