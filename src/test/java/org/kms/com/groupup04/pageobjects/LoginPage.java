package org.kms.com.groupup04.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
    final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(xpath = "//ul[contains(@class,'main_menu')]//a[normalize-space(.)='Login or register']")
    public WebElement eMainMenuItemRegisterLogin;

    @FindBy(xpath = "//input[@name='loginname']")
    public WebElement eLoginNameTextbox;

    @FindBy(xpath = "//input[@name='password']")
    public WebElement ePasswordTextbox;

    @FindBy(xpath = "//button[@title='Login']")
    public WebElement eLoginButton;

    public void navigateToLoginPage(){
        eMainMenuItemRegisterLogin.click();
    }

    public void enterLoginName(String loginName) {
        CommonPage.setText(eLoginNameTextbox, loginName);
    }

    public void enterPassword(String password) {
        CommonPage.setText(ePasswordTextbox, password);
    }

    public void clickLogin() {
        eLoginButton.click();
    }

    public void login(String username, String password) {
        enterLoginName(username);
        enterPassword(password);
        clickLogin();
    }

}
