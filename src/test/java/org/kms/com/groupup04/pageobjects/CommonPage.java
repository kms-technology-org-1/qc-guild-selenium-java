package org.kms.com.groupup04.pageobjects;

import org.kms.com.groupup04.commons.CustomException;
import org.kms.com.groupup04.commons.DTOHolder;
import org.kms.com.groupup04.commons.Waits;
import org.kms.com.groupup04.data.dto.Property;
import org.kms.com.groupup04.utils.StringUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class CommonPage extends BasePage {

    @FindBy(xpath = "//div[@class='oxd-loading-spinner']")
    public WebElement eLoadingIndicator;

    public String tblTable = "//div[@role='table']";
    public String alertMsg = "//div[contains(@class, 'oxd-toast-content')]//*[text()='%s']";

    public String dynamicMainMenuItem = "//ul[@class='oxd-main-menu']//li//span[text()='%s']";
    public String dynamicTopBarMenu = "//a[normalize-space(text())='%s']";
    public String dynamicTopBarMenuItem = "//div[contains(@class,'topbar-body')]//span[normalize-space(text())='%s']//i";
    public String dynamicMainTitle = "//*[self::p or self::h6 or self::h5][text()='%s']";
    public String dynamicButtonByName = "//button[normalize-space(.)='%s']";
    public String dynamicErrorMsgByName = "//label[normalize-space(.)='%s']/../../span[contains(@class,'error-message')]";
    public String dynamicFieldByTxt = "//div[label[contains(., '%s')]]/following::input";
    public String dynamicRecordNameWithLevelTitle = "//div[contains(@class,'oxd-table-card')]//div[text()='%s']";

    public void setText(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    public void setText(WebDriver driver, String text, String xpath, String... dynamicValues) {
        WebElement element = getWebElement(driver, StringUtil.castRestParameter(xpath, dynamicValues));
        setText(element, text);
    }

    public WebElement getWebElement(WebDriver driver, String locator) {
        return driver.findElement(By.xpath(locator));
    }

    public void scrollToMiddle(WebDriver driver, String xpath, String... dynamicValues) {
        WebElement element = getWebElement(driver, StringUtil.castRestParameter(xpath, dynamicValues));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }

    public String waitAndGetText(WebDriver driver, String xpath) {
        WebElement element = Waits.waitForElementVisible(driver, xpath);
        return element.getText();
    }

    public boolean elementIsExisted(String xpath) {
        try {
            getWebElement(driver, xpath);
            return true;
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }

    public void clickToElement(WebDriver driver, String xpath, String... dynamicValues) {
        Waits.waitForElementVisible(driver, xpath, dynamicValues);
        WebElement element = Waits.waitForElementClickable(driver, xpath, dynamicValues);
        element.click();
    }

    public void clickToMainMenuItem(String expectedItem) {
        clickToElement(driver, dynamicMainMenuItem, expectedItem);
    }

    public void clickToDynamicButtonByName(WebDriver driver, String expectedButtonName) {
        scrollToMiddle(driver, dynamicButtonByName, expectedButtonName);
        clickToElement(driver, dynamicButtonByName, expectedButtonName);
    }

    public void inputValueIntoField(String value, String fieldName) {
        Waits.waitForElementVisible(driver, dynamicFieldByTxt, fieldName);
        setText(driver, value, dynamicFieldByTxt, fieldName);
    }

    // Select a dropdown and choose item in top-bar menu
    public void selectDropdownMenuItemByText(String dropdownName, String itemName) {
        clickToElement(driver, dynamicTopBarMenuItem, dropdownName);
        clickToElement(driver, dynamicTopBarMenu, itemName);
    }

    public void verifyMainTitle(String expectedMainTitle) {
        Waits.waitForElementVisible(driver, dynamicMainTitle, expectedMainTitle);
        WebElement mainTitleElement = getWebElement(driver, StringUtil.castRestParameter(dynamicMainTitle, expectedMainTitle));
        String actual = mainTitleElement.getText();
        Assert.assertEquals(actual, expectedMainTitle, "Main Title is not met");
    }

    public void verifyAlertMessage(WebDriver driver, String xpath, String expectedMsg) {
        xpath = StringUtil.castRestParameter(xpath, expectedMsg);
        String actualMsg = waitAndGetText(driver, xpath);
        Assert.assertTrue(actualMsg.contains(expectedMsg), "The alert message " + expectedMsg + " is not met");
    }

    public void verifyValidationErrorMessage(String expectedMsg, String fieldName) {
        Waits.waitForElementVisible(driver, dynamicErrorMsgByName, fieldName);
        WebElement eErrorMsg = getWebElement(driver, StringUtil.castRestParameter(dynamicErrorMsgByName, fieldName));
        String actualValidationMsg = eErrorMsg.getText().trim();
        Assert.assertEquals(actualValidationMsg, expectedMsg);
    }

    public void verifyRecordWithTitleNameIsDisplayed(String propertyName, String propertyValue) throws CustomException {
        if (propertyValue.equals("HRM_PROPERTY_RANDOM")) {
            switch (propertyName.toLowerCase()) {
                case "skills":
                    Property skillProperty = DTOHolder.getInstance().getSkillProperty();
                    propertyValue = skillProperty.getPropertyValue();
                    break;
                case "languages":
                    Property languageProperty = DTOHolder.getInstance().getLanguageProperty();
                    propertyValue = languageProperty.getPropertyValue();
                    break;
                default:
                    throw new CustomException("The Property " + propertyName + " is not suitable.");
            }
        }
        boolean isActualDisplay = false;
        Waits.waitForElementVisible(driver, tblTable);
        String lblRecordFound = StringUtil.castRestParameter(dynamicRecordNameWithLevelTitle, propertyValue);
        Waits.waitForElementVisible(driver, lblRecordFound);
        isActualDisplay = elementIsExisted(lblRecordFound);
        Assert.assertTrue(isActualDisplay, "The title does not exist");
    }

    public void verifyThePageTitle(String expectedTitle) {
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    public void verifyAlertMsg(String msg) {
        verifyAlertMessage(driver, alertMsg, msg);
    }
}