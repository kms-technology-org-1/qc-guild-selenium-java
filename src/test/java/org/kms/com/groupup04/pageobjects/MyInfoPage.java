package org.kms.com.groupup04.pageobjects;

import org.kms.com.groupup04.commons.CommonConstants;
import org.kms.com.groupup04.commons.DTOHolder;
import org.kms.com.groupup04.commons.Waits;
import org.kms.com.groupup04.data.dto.Property;
import org.kms.com.groupup04.utils.AppConfigs;
import org.kms.com.groupup04.utils.DataGenerator;
import org.kms.com.groupup04.utils.StringUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class MyInfoPage extends CommonPage {

    public String dynamicPropertyOptionByLabel = "//div[contains(@class, 'oxd-grid-item') and .//label[normalize-space(text())='%s']]//div[@role='listbox']//div[@role='option']/span";
    public String dynamicTabNameByTxt = "//div[@role='tablist']//a[normalize-space(.) = '%s']";
    public String dynamicBtnUnderQualification = "//h6[normalize-space(.) = '%s']/following-sibling::button[normalize-space(.) = '%s']";
    public String dynamicOptionByLabel = "//div[contains(concat(' ', @class, ' '), ' oxd-input-group ')and .//label[normalize-space(text()) = '%s']]//i";
    public String dynamicMyInfoFieldByTxt = "//label[text()='%s']//parent::div/following-sibling::div/input";
    public String dynamicMyInfoFieldByTxtArea = "//label[text()='%s']//parent::div/following-sibling::div/textarea";

    public void selectItemInCustomDropdown(WebDriver driver, String parentXpath, String childXpath, String expectedItemText) {
        getWebElement(driver, parentXpath).click();

        List<WebElement> allItems = new WebDriverWait(driver, Duration.ofSeconds(AppConfigs.TIMEOUT_MEDIUM))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));

        WebElement targetItem = allItems.stream()
                .filter(item -> item.getText().trim().equals(expectedItemText))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No element found with text: " + expectedItemText));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", targetItem);
        Waits.waitForElementVisible(driver, targetItem);
        targetItem.click();

    }

    public void selectTabName(String tabName) {
        clickToElement(driver, dynamicTabNameByTxt, tabName);
    }

    public void selectFuncBtnInQualifications(String btnName, String sectionName) {
        scrollToMiddle(driver, dynamicBtnUnderQualification, sectionName, btnName);
        clickToElement(driver, dynamicBtnUnderQualification, sectionName, btnName);
    }

    public void generateRandomStringWithLength(int length, String fieldName) {
        String randomString = DataGenerator.generateRandomString(length);
        String fieldByXpath = StringUtil.castRestParameter(dynamicMyInfoFieldByTxt, fieldName);
        scrollToMiddle(driver, fieldByXpath);
        setText(driver, randomString, fieldByXpath);
    }

    public void generateRandomCharacterWithLength(String randomCharacter, int length, String fieldName) {
        String randomText = "";
        if (randomCharacter.contains("STRING")) {
            randomText = DataGenerator.generateRandomString(length);
        } else if (randomCharacter.contains("NUMBER")) {
            randomText = String.valueOf(DataGenerator.generateRandomNumber(length));
        } else {
            randomText = randomCharacter;
        }
        String fieldByXpath = StringUtil.castRestParameter(dynamicMyInfoFieldByTxt, fieldName);
        scrollToMiddle(driver, fieldByXpath);
        setText(driver, randomText, fieldByXpath);
    }

    public void generateRandomStringWithLengthInTextArea(int length, String fieldName) {
        String randomString = DataGenerator.generateRandomString(length);
        String fieldByXpath = StringUtil.castRestParameter(dynamicMyInfoFieldByTxtArea, fieldName);
        scrollToMiddle(driver, fieldByXpath);
        setText(driver, randomString, fieldByXpath);
    }

    public void updateTheSkillOnQualifications(String skillValue) {
        if (skillValue != null && skillValue.equals("HRM_PROPERTY_RANDOM")) {
            Property property = DTOHolder.getInstance().getSkillProperty();
            skillValue = property.getPropertyValue();
        }
        selectFuncBtnInQualifications(CommonConstants.ADD, CommonConstants.SKILLS);
        Waits.waitForLoadingIndicatorToDisappear(driver, eLoadingIndicator);
        String skillDdl = StringUtil.castRestParameter(dynamicOptionByLabel, CommonConstants.SKILL);
        String skillOptionDdl = StringUtil.castRestParameter(dynamicPropertyOptionByLabel, CommonConstants.SKILL);

        if (skillValue != null && !skillValue.isEmpty()) {
            scrollToMiddle(driver, skillDdl);
            selectItemInCustomDropdown(driver, skillDdl, skillOptionDdl, skillValue);
        }
    }

    public void updateLanguage(String languageValue, String fluencyValue, String competencyValue) {
        if (languageValue != null && languageValue.equals("HRM_PROPERTY_RANDOM")) {
            Property property = DTOHolder.getInstance().getLanguageProperty();
            languageValue = property.getPropertyValue();
        }
        selectFuncBtnInQualifications(CommonConstants.ADD, CommonConstants.LANGUAGES);
        Waits.waitForLoadingIndicatorToDisappear(driver, eLoadingIndicator);
        String languageDdl = StringUtil.castRestParameter(dynamicOptionByLabel, CommonConstants.LANGUAGE);
        String fluencyDdl = StringUtil.castRestParameter(dynamicOptionByLabel, CommonConstants.FLUENCY);
        String competencyDdl = StringUtil.castRestParameter(dynamicOptionByLabel, CommonConstants.COMPETENCY);

        String languageOptionDdl = StringUtil.castRestParameter(dynamicPropertyOptionByLabel, CommonConstants.LANGUAGE);
        String fluencyOptionDdl = StringUtil.castRestParameter(dynamicPropertyOptionByLabel, CommonConstants.FLUENCY);
        String competencyOptionDdl = StringUtil.castRestParameter(dynamicPropertyOptionByLabel, CommonConstants.COMPETENCY);

        if (languageValue != null && !languageValue.isEmpty()) {
            scrollToMiddle(driver, languageDdl);
            selectItemInCustomDropdown(driver, languageDdl, languageOptionDdl, languageValue);
        }
        if (fluencyValue != null && !fluencyValue.isEmpty()) {
            scrollToMiddle(driver, fluencyDdl);
            selectItemInCustomDropdown(driver, fluencyDdl, fluencyOptionDdl, fluencyValue);
        }
        if (competencyValue != null && !competencyValue.isEmpty()) {
            scrollToMiddle(driver, competencyDdl);
            selectItemInCustomDropdown(driver, competencyDdl, competencyOptionDdl, competencyValue);
        }
    }
}