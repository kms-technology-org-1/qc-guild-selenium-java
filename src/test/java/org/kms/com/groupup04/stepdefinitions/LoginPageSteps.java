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

    @When("user perform login with valid credentials")
    public void userPerformLoginWithValidCredentials() {
        loginPage.login(config.getProperty("username"), config.getProperty("password"));
    }

    @And("A user is on {string} page")
    public void userIsOnSpecificPage(String page) {
        Assert.assertEquals(driver.getCurrentUrl(), config.getProperty("base_url") + page);
    }
}