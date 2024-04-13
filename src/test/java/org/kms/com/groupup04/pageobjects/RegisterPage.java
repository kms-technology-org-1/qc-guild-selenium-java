package org.kms.com.groupup04.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage {
    final WebDriver driver;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(xpath = "//ul[contains(@class,'main_menu')]//a[normalize-space(.)='Login or register']")
    public WebElement eMainMenuItemRegister;

    @FindBy(xpath = "//button[@title='Continue']")
    public WebElement eContinueButton;

    @FindBy(xpath = "//input[@id='AccountFrm_firstname']")
    public WebElement eFirstNameTextbox;

    public void navigateToRegisterPage(){
        eMainMenuItemRegister.click();
        eContinueButton.click();
    }

    public void enterFirstName(String firstName) {
        CommonPage.setText(eFirstNameTextbox, firstName);
    }
}
