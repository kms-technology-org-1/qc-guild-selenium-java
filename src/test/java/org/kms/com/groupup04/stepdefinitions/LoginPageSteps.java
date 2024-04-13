package org.kms.com.groupup04.stepdefinitions;

import io.cucumber.java.en.Given;
import org.kms.com.groupup04.pageobjects.BasePage;
import org.kms.com.groupup04.pageobjects.LoginPage;
import org.openqa.selenium.support.PageFactory;

public class LoginPageSteps extends BasePage {
    LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);

    @Given("user navigate to Login page")
    public void userNavigateToRegisterPage() {
        loginPage.navigateToLoginPage();
    }

}