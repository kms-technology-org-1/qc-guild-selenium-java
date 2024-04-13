package org.kms.com.groupup04.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
    final WebDriver driver;
    final String LOGIN_PAGE_URL = "/web/index.php/auth/login";

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(xpath = "//input[@name='username']")
    public WebElement eLoginNameTextbox;

    @FindBy(xpath = "//input[@name='password']")
    public WebElement ePasswordTextbox;

    @FindBy(xpath = "//button[contains(@type,'submit')]")
    public WebElement eLoginButton;

    public void navigateToLoginPage(){
        driver.get(config.getProperty("base_url") + LOGIN_PAGE_URL);
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
