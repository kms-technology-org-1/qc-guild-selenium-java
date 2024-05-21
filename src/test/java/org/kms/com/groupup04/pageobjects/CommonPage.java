package org.kms.com.groupup04.pageobjects;

import org.kms.com.groupup04.commons.CustomException;
import org.kms.com.groupup04.commons.DTOHolder;
import org.kms.com.groupup04.data.dto.EmployeeInfo;
import org.kms.com.groupup04.data.dto.Property;
import org.kms.com.groupup04.utils.AppConfigs;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class CommonPage extends BasePage {

    @FindBy(xpath = "//div[@class='oxd-loading-spinner']")
    public static WebElement eLoadingIndicator;

    public String tblTable = "//div[@role='table']";

    public String dynamicMainMenuItem = "//ul[@class='oxd-main-menu']//li//span[text()='%s']";
    public String dynamicTopBarMenu = "//a[normalize-space(text())='%s']";
    public String dynamicTopBarMenuItem = "//div[contains(@class,'topbar-body')]//span[normalize-space(text())='%s']//i";
    public String dynamicMainTitle = "//*[self::p or self::h6 or self::h5][text()='%s']";
    public String dynamicButtonByName = "//button[normalize-space(.)='%s']";
    public String dynamicErrorMsgByName = "//label[normalize-space(.)='%s']/../../span[contains(@class,'error-message')]";
    public String dynamicFieldByTxt = "//div[label[contains(., '%s')]]/following::input";
    public String dynamicRecordNameWithLevelTitle = "//div[contains(@class,'oxd-table-card')]//div[text()='%s']";
    public String dynamicUserRecordByUserName = "//div[@role='rowgroup']//div[@class='oxd-table-card']//div[normalize-space(.)='%s']/..//i[contains(@class, 'bi-trash')]";
    public String dynamicBtnDeletePropertyRecord = "//div[@role='rowgroup']//div[@class='oxd-table-card']//div[normalize-space(.)='%s']/..//i[contains(@class, 'bi-trash')]";

    public void sleepInSecond(long second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setText(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    public void setText(WebDriver driver, String xpath, String text) {
        WebElement element = driver.findElement(By.xpath(xpath));
        element.clear();
        element.sendKeys(text);
    }

    public void setText(WebDriver driver, String text, String locator, String... dynamicValues) {
        String dynamicLocator = castRestParameter(locator, dynamicValues);
        WebElement element = driver.findElement(By.xpath(dynamicLocator));
        element.clear();
        element.sendKeys(text);
    }

    public String castRestParameter(String locator, String... values) {
        locator = String.format(locator, (Object[]) values);
        return locator;
    }

    public WebElement getWebElement(WebDriver driver, String locator) {
        return driver.findElement(By.xpath(locator));
    }

    public void scrollToMiddle(WebDriver driver, String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        ((JavascriptExecutor) driver).executeScript(
                "var element = arguments[0];" +
                        "var elementRect = element.getBoundingClientRect();" +
                        "var absoluteElementTop = elementRect.top + window.pageYOffset;" +
                        "var middle = absoluteElementTop - (window.innerHeight / 2) + (elementRect.height / 2);" +
                        "window.scrollTo(0, middle);",
                element
        );
    }

    public void scrollToMiddle(WebDriver driver, String locator, String... dynamicValues) {
        ((JavascriptExecutor) driver).executeScript("var element = arguments[0];" +
                        "var elementRect = element.getBoundingClientRect();" +
                        "var absoluteElementTop = elementRect.top + window.pageYOffset;" +
                        "var middle = absoluteElementTop - (window.innerHeight / 2) + (elementRect.height / 2);" +
                        "window.scrollTo(0, middle);",
                getWebElement(driver, castRestParameter(locator, dynamicValues)));
    }

    public WebElement waitUntilElementLocated(WebDriver driver, String xpath) {
        By locator = By.xpath(xpath);
        return new WebDriverWait(driver, Duration.ofSeconds(AppConfigs.TIMEOUT_MEDIUM)).until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void waitForElementClickable(WebDriver driver, String locator, String... dynamicValues) {
        String dynamicLocator = castRestParameter(locator, dynamicValues);
//        WebElement element = driver.findElement(By.xpath(dynamicLocator));
        WebElement element = waitUntilElementIsVisible(driver, dynamicLocator);
        new WebDriverWait(driver, Duration.ofSeconds(AppConfigs.TIMEOUT_SMALL)).until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement waitUntilElementIsClickable(WebDriver driver, String xpath) {
        WebElement element = waitUntilElementIsVisible(driver, xpath);
        return new WebDriverWait(driver, Duration.ofSeconds(AppConfigs.TIMEOUT_SMALL)).until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForElementVisible(WebDriver driver, WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(AppConfigs.TIMEOUT_MEDIUM))
                .until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementVisible(WebDriver driver, String locator, String... dynamicValues) {
        String dynamicLocator = castRestParameter(locator, dynamicValues);
        By byLocator = By.xpath(dynamicLocator);
        new WebDriverWait(driver, Duration.ofSeconds(AppConfigs.TIMEOUT_SMALL)).until(ExpectedConditions.visibilityOfElementLocated(byLocator));
    }

    public WebElement waitUntilElementIsVisible(WebDriver driver, String xpath) {
        WebElement element = waitUntilElementLocated(driver, xpath);
        return new WebDriverWait(driver, Duration.ofSeconds(AppConfigs.TIMEOUT_MEDIUM)).until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForLoadingIndicatorToDisappear(WebDriver driver, WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(AppConfigs.TIMEOUT_SMALL)).until(ExpectedConditions.invisibilityOf(element));
    }

    public String waitAndGetText(WebDriver driver, String xpath) {
        WebElement element = waitUntilElementLocated(driver, xpath);
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

    public void clickToElement(WebDriver driver, String locator, String... dynamicValues) {
        getWebElement(driver, castRestParameter(locator, dynamicValues)).click();
    }

    public void clickToElement(WebDriver driver, String xpath) {
        WebElement element = waitUntilElementIsClickable(driver, xpath);
        element.click();
    }

    public void clickToMainMenuItem(String expectedItem) {
        waitForElementClickable(driver, dynamicMainMenuItem, expectedItem);
        clickToElement(driver, dynamicMainMenuItem, expectedItem);
    }

    public void clickToDynamicButtonByName(WebDriver driver, String expectedButtonName) {
        String btn = dynamicButtonByName.replace("%s", expectedButtonName);
        waitForLoadingIndicatorToDisappear(driver, eLoadingIndicator);
        scrollToMiddle(driver, dynamicButtonByName, expectedButtonName);
        clickToElement(driver, btn);
    }

    public void inputValueIntoField(String value, String fieldName) {
        waitForElementVisible(driver, dynamicFieldByTxt, fieldName);
        setText(driver, value, dynamicFieldByTxt, fieldName);
    }

    // Select a dropdown and choose item in top-bar menu
    public void selectDropdownMenuItemByText(String dropdownName, String itemName) {
        waitForElementClickable(driver, dynamicTopBarMenuItem, dropdownName);
        clickToElement(driver, dynamicTopBarMenuItem, dropdownName);

        waitForElementClickable(driver, dynamicTopBarMenu, itemName);
        clickToElement(driver, dynamicTopBarMenu, itemName);
    }

    public void verifyMainTitle(String expectedMainTitle) {
        waitForElementVisible(driver, dynamicMainTitle, expectedMainTitle);
        WebElement mainTitleElement = driver.findElement(By.xpath(String.format(dynamicMainTitle, expectedMainTitle)));
        String actual = mainTitleElement.getText();
        Assert.assertEquals(actual, expectedMainTitle, "Main Title is not met");
    }

    public void verifyAlert(WebDriver driver, String xpath, String expectedMsg) {
        sleepInSecond(2);
        String actualMsg = waitAndGetText(driver, xpath);
        Assert.assertTrue(actualMsg.contains(expectedMsg), "The alert message is not met");
    }

    public void verifyValidationErrorMessage(String expectedMsg, String fieldName) {
        waitForElementVisible(driver, dynamicErrorMsgByName, fieldName);
        WebElement eErrorMsg = driver.findElement(By.xpath(String.format(dynamicErrorMsgByName, fieldName)));
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
        waitUntilElementIsVisible(driver, tblTable);
        String lblRecordFound = dynamicRecordNameWithLevelTitle.replace("%s", propertyValue);
        waitUntilElementIsVisible(driver, lblRecordFound);
        isActualDisplay = elementIsExisted(lblRecordFound);
        Assert.assertTrue(isActualDisplay, "The title does not exist");
    }

    public void verifyThePageTitle(String expectedTitle) {
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    public void deleteNewlyCreatedESSUser() {
        EmployeeInfo employeeInfo = DTOHolder.getInstance().getEmployeeInfoDTO();
        String userName = employeeInfo.getUsername();
        String btnDeleteUser;
        btnDeleteUser = dynamicUserRecordByUserName.replace("%s", userName);
        clickToElement(driver, btnDeleteUser);
    }
}