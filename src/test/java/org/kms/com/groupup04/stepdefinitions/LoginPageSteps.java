package org.kms.com.groupup04.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.kms.com.groupup04.pageobjects.BasePage;
import org.kms.com.groupup04.pageobjects.LoginPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPageSteps extends BasePage {

    LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);

    @Given("user navigate to Login page")
    public void userNavigateToRegisterPage() {
        loginPage.navigateToLoginPage();
    }

    @When("user logged in that page by Admin role")
    public void userPerformLoginWithValidCredentials() {
        loginPage.login(config.getProperty("username"), config.getProperty("password"));
    }

    @And("user is on {string} page")
    public void userIsOnSpecificPage(String page) {
        Assert.assertEquals(driver.getCurrentUrl(), config.getProperty("base_url") + page);
    }

    @When("user logout their account")
    public void logoutAccount() {
        loginPage.logout();
    }

    @And("user login with newly created ESS account")
    public void userLoginWithNewlyCreatedESSAccount() {
        loginPage.loginWithESSAccount();
    }

    @And("user login with new password")
    public void userLoginWithNewPassword() {
        loginPage.loginWithESSAccount();
    }
}