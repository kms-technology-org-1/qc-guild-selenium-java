package org.kms.com.groupup04.pageobjects;

import org.kms.com.groupup04.commons.DTOHolder;
import org.kms.com.groupup04.data.dto.EmployeeInfo;
import org.kms.com.groupup04.utils.AppConfigs;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.util.List;

import java.time.Duration;

public class CommonPage extends BasePage {

    @FindBy(xpath = "//div[@class='oxd-topbar-header-title']//span/h6[1]")
    public static WebElement eHeaderText;

    @FindBy(xpath = "//div[@id='oxd-toaster_1']//p[contains(@class, 'oxd-text--toast-message')]")
    public static WebElement eToastMessage;

    @FindBy(xpath = "//div[@class='oxd-loading-spinner']")
    public static WebElement eLoadingIndicator;

    public static final String pageTitleHeader = "//div[@class='orangehrm-background-container']//h5 | //div[@class='orangehrm-background-container']//h6";
    public static final String labelRecordsFound = "//span[contains(.,'Record Found') or contains(.,'Records Found')]";
    public static final String tblTable = "//div[@role='table']";


    public static final String dynamicMainMenuItem = "//ul[@class='oxd-main-menu']//li//span[text()='%s']";
    public static final String dynamicTopBarMenu = "//a[normalize-space(text())='%s']";
    public static final String dynamicTopBarMenuItem = "//div[contains(@class,'topbar-body')]//span[normalize-space(text())='%s']//i";
    public static final String dynamicMainTitle = "//*[self::p or self::h6 or self::h5][text()='%s']";
    public static final String dynamicButtonByName = "//button[normalize-space(.)='%s']";
    public static final String dynamicErrorMsgByName = "//label[normalize-space(.)='%s']/../../span[contains(@class,'error-message')]";
    public String dynamicMainTitleTxt ="//h6[contains(@class, 'orangehrm-main-title')and(text()='%s')]";
    public String dynamicFieldByTxt ="//div[label[contains(., '%s')]]/following::input";
    public String dynamicRecordNameWithLevelTitle = "//div[contains(@class,'oxd-table-card')]//div[text()='%s']";
    public String dynamicUserRecordByUserName = "//div[@role='rowgroup']//div[@class='oxd-table-card']//div[normalize-space(.)='%s']/..//i[contains(@class, 'bi-trash')]";
    public String dynamicBtnDeleteNationalityRecord = "//div[@role='rowgroup']//div[@class='oxd-table-card']//div[normalize-space(.)='%s']/..//i[contains(@class, 'bi-trash')]";


    public void verifyThePageTitle(String expectedTitle) {
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    public void setText(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    public static void setText(WebDriver driver, String text, String locator, String... dynamicValues) {
        String dynamicLocator = castRestParameter(locator, dynamicValues);
        WebElement element = driver.findElement(By.xpath(dynamicLocator));
        element.clear();
        element.sendKeys(text);
    }

    public static String castRestParameter(String locator, String... values) {
        locator = String.format(locator, (Object[]) values);
        return locator;
    }

    public static By getByXpath(String locator) {
        return By.xpath(locator);
    }

    public static WebElement getWebElement(WebDriver driver, String locator) {
        return driver.findElement(getByXpath(locator));
    }

    public void waitForElementClickable(WebDriver driver, WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(AppConfigs.TIMEOUT_SMALL)).until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForElementVisible(WebDriver driver, WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(AppConfigs.TIMEOUT_MEDIUM))
                .until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForAlertPresence(WebDriver driver) {
        new WebDriverWait(driver, Duration.ofSeconds(AppConfigs.TIMEOUT_SMALL))
                .until(ExpectedConditions.alertIsPresent());
    }

    public static void waitForElementClickable(WebDriver driver, String locator, String... dynamicValues) {
        String dynamicLocator = castRestParameter(locator, dynamicValues);
        WebElement element = driver.findElement(By.xpath(dynamicLocator));
        new WebDriverWait(driver, Duration.ofSeconds(AppConfigs.TIMEOUT_SMALL)).until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForElementVisible(WebDriver driver, String locator, String... dynamicValues) {
        String dynamicLocator = castRestParameter(locator, dynamicValues);
        By byLocator = By.xpath(dynamicLocator);
        new WebDriverWait(driver, Duration.ofSeconds(AppConfigs.TIMEOUT_SMALL)).until(ExpectedConditions.visibilityOfElementLocated(byLocator));
    }

    public static void waitForLoadingIndicatorToDisappear(WebDriver driver, WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(AppConfigs.TIMEOUT_SMALL)).until(ExpectedConditions.invisibilityOf(element));
    }

    public static void waitForLoadingIndicatorAndToastMessage(WebDriver driver, WebElement eLoadingIndicator, WebElement eToastMsg) {
        new WebDriverWait(driver, Duration.ofSeconds(AppConfigs.TIMEOUT_MEDIUM))
                .until(ExpectedConditions.or(
                        ExpectedConditions.invisibilityOf(eLoadingIndicator),
                        ExpectedConditions.visibilityOf(eToastMsg)));
    }

    public static void clickToElement(WebDriver driver, String locator, String... dynamicValues) {
        getWebElement(driver, castRestParameter(locator, dynamicValues)).click();
    }

    public static void clickToElement(WebElement element) {
        element.click();
    }


    public static void scrollToMiddle(WebDriver driver, String locator, String... dynamicValues) {
        ((JavascriptExecutor) driver).executeScript("var element = arguments[0];" +
                        "var elementRect = element.getBoundingClientRect();" +
                        "var absoluteElementTop = elementRect.top + window.pageYOffset;" +
                        "var middle = absoluteElementTop - (window.innerHeight / 2) + (elementRect.height / 2);" +
                        "window.scrollTo(0, middle);",
                getWebElement(driver, castRestParameter(locator, dynamicValues)));
    }

    public static void scrollToMiddle(WebDriver driver, String xpath) {
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

    public static void clickToMainMenuItem(String expectedItem) {
        waitForElementClickable(driver, dynamicMainMenuItem, expectedItem);
        clickToElement(driver, dynamicMainMenuItem, expectedItem);
    }

    public static void clickToTopBarItem(String expectedItem) {
        waitForElementClickable(driver, dynamicTopBarMenu, expectedItem);
        clickToElement(driver, dynamicTopBarMenu, expectedItem);
    }

    public void clickToDynamicButtonByName(WebDriver driver, String expectedButtonName) {
        String btn =  dynamicButtonByName.replace("%s", expectedButtonName);
        scrollToMiddle(driver, dynamicButtonByName, expectedButtonName);
        WebElement element = waitUntilElementIsClickable(driver, btn);
        clickToElement(element);
    }

    //Click item in top-bar menu
    public static void clickToTopBarMenuItem(String itemName) {
        waitForElementClickable(driver, dynamicTopBarMenu, itemName);
        clickToElement(driver, dynamicTopBarMenu, itemName);
    }

    // Select a dropdown and choose item in top-bar menu
    public static void selectDropdownMenuItemByText(String dropdownName, String itemName) {
        waitForElementClickable(driver, dynamicTopBarMenuItem, dropdownName);
        clickToElement(driver, dynamicTopBarMenuItem, dropdownName);

        waitForElementClickable(driver, dynamicTopBarMenu, itemName);
        clickToElement(driver, dynamicTopBarMenu, itemName);
    }

    public static void selectItemInDefaultDropdown(WebElement element, String itemText) {
        Select select = new Select(element);
        select.selectByVisibleText(itemText);
        Assert.assertEquals(select.getFirstSelectedOption().getText(), itemText);
    }

    public void verifyMainTitle(String expectedMainTitle) {
        waitForElementVisible(driver, dynamicMainTitle, expectedMainTitle);
        WebElement mainTitleElement = driver.findElement(By.xpath(String.format(dynamicMainTitle, expectedMainTitle)));
        String actual = mainTitleElement.getText();
        Assert.assertEquals(actual, expectedMainTitle, "Main Title is not matched");
    }

    public static void verifyAlert(WebDriver driver, String xpath, String expectedMsg) {
        String actualMsg = waitAndGetText(driver, xpath);
        Assert.assertTrue(actualMsg.contains(expectedMsg), "The alert message is not matched");
    }

    public static void verifyPageHeader(String expectedHeader) {
        waitForElementVisible(driver, eHeaderText);
        String actualHeader = eHeaderText.getText().trim();
        Assert.assertEquals(actualHeader, expectedHeader, "The page header is not matched");
    }

    public static void verifyTheHeaderDisplayed(String expectedHeader) {
        String actualMainTitle = eHeaderText.getText().trim();
        Assert.assertEquals(actualMainTitle, expectedHeader, "The page header is not matched");
    }

    public void verifyValidationErrorMessage(String expectedMsg, String fieldName) {
        waitForElementVisible(driver, dynamicErrorMsgByName, fieldName);
        WebElement eErrorMsg = driver.findElement(By.xpath(String.format(dynamicErrorMsgByName, fieldName)));
        String actualValidationMsg = eErrorMsg.getText().trim();
        Assert.assertEquals(actualValidationMsg, expectedMsg);
    }

    public boolean isElementDisplayed(WebDriver driver, String locator, String... dynamicValues) {
        return getWebElement(driver, castRestParameter(locator, dynamicValues)).isDisplayed();
    }

    public void verifyMainTitleIsDisplayed(String expectedMainTitle){
        CommonPage.waitForElementVisible(driver, dynamicMainTitleTxt, expectedMainTitle);
        CommonPage.verifyElementIsDisplayed(Duration.ofSeconds(AppConfigs.TIMEOUT_MEDIUM), dynamicMainTitleTxt, expectedMainTitle);
    }

    public void inputValueIntoField(String value, String fieldName){
        CommonPage.waitForElementVisible(driver, dynamicFieldByTxt, fieldName);
        CommonPage.setText(driver, value, dynamicFieldByTxt, fieldName);
    }

//    Part 2 ---------------------

    public static void verifyElementIsDisplayed(Duration timeout, String locator, String... dynamicValues) {
        String dynamicLocator = castRestParameter(locator, dynamicValues);
        By byLocator = By.xpath(dynamicLocator);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
        Assert.assertTrue(element.isDisplayed(), "The element is not displayed");
    }

    public static WebElement waitUntilElementLocated(WebDriver driver, String xpath) {
        By locator = By.xpath(xpath);
        return new WebDriverWait(driver, Duration.ofSeconds(AppConfigs.TIMEOUT_MEDIUM)).until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static WebElement waitUntilElementIsVisible(WebDriver driver, String xpath) {
        WebElement element = waitUntilElementLocated(driver, xpath);
        return new WebDriverWait(driver, Duration.ofSeconds(AppConfigs.TIMEOUT_MEDIUM)).until(ExpectedConditions.visibilityOf(element));
    }

//    -----------
public WebElement waitUntilElementIsClickable(WebDriver driver, String xpath) {
    WebElement element = waitUntilElementIsVisible(driver, xpath);
    return new WebDriverWait(driver, Duration.ofSeconds(AppConfigs.TIMEOUT_SMALL)).until(ExpectedConditions.elementToBeClickable(element));
}

    public void verifyRecordWithTitleNameIsDisplayed(String titleName){
        boolean isActualDisplay = false;
        waitUntilElementIsVisible(driver, tblTable);
        String lblRecordFound = dynamicRecordNameWithLevelTitle.replace("%s", titleName);
        waitUntilElementIsVisible(driver, lblRecordFound);
        isActualDisplay = elementIsExisted(lblRecordFound);
        Assert.assertTrue(isActualDisplay, "The title does not exist");
    }

//    -----------
    public static String waitAndGetText(WebDriver driver, String xpath) {
        WebElement element = waitUntilElementLocated(driver, xpath);
        return element.getText();
    }

    public static void waitPageHeaderIsLoaded() {
        waitUntilElementIsVisible(driver, pageTitleHeader);
    }

    public static int getNumberOfRecordsFound() {
        try {
            waitPageHeaderIsLoaded();
            String fullText = waitAndGetText(driver, labelRecordsFound);
            String[] numberArray = fullText.replaceAll("[^0-9]", "").split("");
            if (numberArray.length == 0) {
                return 0;
            }
            StringBuilder numberBuilder = new StringBuilder();
            for (String digit : numberArray) {
                numberBuilder.append(digit);
            }
            return Integer.parseInt(numberBuilder.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static boolean elementIsExisted(String xpath) {
        try {
            getWebElement(driver, xpath);
            return true;
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }

    public int countNumberOfElementsByXPath(String xpath) {
        List<WebElement> elements = new WebDriverWait(driver, Duration.ofSeconds(AppConfigs.TIMEOUT_SMALL)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
        return elements.size();
    }


//    Delete method-----------

    public void deleteNewlyCreatedESSUser() {
        EmployeeInfo employeeInfo = DTOHolder.getInstance().getEmployeeInfoDTO();
        String userName = employeeInfo.getUsername();
        String btnDeleteUser;
        btnDeleteUser = dynamicUserRecordByUserName.replace("%s", userName);
        WebElement element = waitUntilElementIsClickable(driver, btnDeleteUser);
        clickToElement(element);
    }

    public void deleteRecordToCleanEnv(String recordName) {
        String btnToDeleteRecord = dynamicBtnDeleteNationalityRecord.replace("%s", recordName);
        scrollToMiddle(driver, btnToDeleteRecord);
        WebElement element = waitUntilElementIsClickable(driver, btnToDeleteRecord);
        clickToElement(element);
    }
}
