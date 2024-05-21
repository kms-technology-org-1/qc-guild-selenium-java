package org.kms.com.groupup04.pageobjects;

import org.kms.com.groupup04.commons.DTOHolder;
import org.kms.com.groupup04.data.dto.EmployeeInfo;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends CommonPage {
    final String LOGIN_PAGE_URL = "/web/index.php/auth/login";

    @FindBy(xpath = "//input[@name='username']")
    public WebElement eLoginNameTxt;

    @FindBy(xpath = "//input[@name='password']")
    public WebElement ePasswordTxt;

    @FindBy(xpath = "//button[contains(@type,'submit')]")
    public WebElement eLoginBtn;

    @FindBy(xpath = "//div[@class='oxd-topbar-header-userarea']//li[contains(@class, 'oxd-userdropdown')]")
    public static WebElement eUserProfileDdl;

    @FindBy(xpath = "//ul[@role='menu']//a[text()='Logout']")
    public static WebElement eLogoutDdo;

    public String ddlUserProfile = "//div[@class='oxd-topbar-header-userarea']//li[contains(@class, 'oxd-userdropdown')]";
    public String ddoLogout = "//ul[@role='menu']//a[text()='Logout']";

    public void navigateToLoginPage() {
        driver.get(config.getProperty("base_url") + LOGIN_PAGE_URL);
    }

    public void enterLoginName(String loginName) {
        setText(eLoginNameTxt, loginName);
    }

    public void enterPassword(String password) {
        setText(ePasswordTxt, password);
    }

    public void clickLogin() {
        eLoginBtn.click();
    }

    public void login(String username, String password) {
        enterLoginName(username);
        enterPassword(password);
        clickLogin();
    }

    public void logout() {
        clickToElement(driver, ddlUserProfile);
        clickToElement(driver, ddoLogout);
    }

    public void loginWithESSAccount() {
        EmployeeInfo employeeInfo = DTOHolder.getInstance().getEmployeeInfoDTO();
        setText(eLoginNameTxt, employeeInfo.getUsername());
        setText(ePasswordTxt, employeeInfo.getPassword());
        clickLogin();
    }
}