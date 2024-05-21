package org.kms.com.groupup04.pageobjects;

import org.kms.com.groupup04.commons.DTOHolder;
import org.kms.com.groupup04.data.dto.Property;
import org.kms.com.groupup04.utils.AppConfigs;
import org.kms.com.groupup04.utils.DataGenerator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MyInfoPage extends CommonPage {

    public String skillOptionDdl = "//div[contains(@class, 'oxd-grid-item') and .//label[normalize-space(text())='Skill']]//div[@role='listbox']//div[@role='option']/span";
    public String languageOptionDdl = "//div[contains(@class, 'oxd-grid-item') and .//label[normalize-space(text())='Language']]//div[@role='listbox']//div[@role='option']/span";
    public String fluencyOptionDdl = "//div[contains(@class, 'oxd-grid-item') and .//label[normalize-space(text())='Fluency']]//div[@role='listbox']//div[@role='option']/span";
    public String competencyOptionDdl = "//div[contains(@class, 'oxd-grid-item') and .//label[normalize-space(text())='Competency']]//div[@role='listbox']//div[@role='option']/span";

    public String dynamicTabNameByTxt = "//div[@role='tablist']//a[normalize-space(.) = '%s']";
    public String dynamicBtnUnderQualification = "//h6[normalize-space(.) = '%s']/following-sibling::button[normalize-space(.) = '%s']";
    public String dynamicOptionByLabel = "//div[contains(concat(' ', @class, ' '), ' oxd-input-group ')and .//label[normalize-space(text()) = '%s']]//i";
    public String dynamicMyInfoFieldByTxt = "//label[text()='%s']//parent::div/following-sibling::div/input";
    public String dynamicMyInfoFieldByTxtArea = "//label[text()='%s']//parent::div/following-sibling::div/textarea";

    public void selectItemInCustomDropdown(WebDriver driver, String parentXpath, String childXpath, String expectedItemText) {
        getWebElement(driver, parentXpath).click();
        sleepInSecond(2);

        List<WebElement> allItems = new WebDriverWait(driver, Duration.ofSeconds(AppConfigs.TIMEOUT_LARGE))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));

        for (WebElement childItem : allItems) {
            if (childItem.getText().trim().equals(expectedItemText)) {
                ((JavascriptExecutor) driver).executeScript("var element = arguments[0];" +
                        "var elementRect = element.getBoundingClientRect();" +
                        "var absoluteElementTop = elementRect.top + window.pageYOffset;" +
                        "var middle = absoluteElementTop - (window.innerHeight / 2) + (elementRect.height / 2);" +
                        "window.scrollTo(0, middle);", childItem);
                sleepInSecond(1);
                childItem.click();
                sleepInSecond(2);
                break;
            }
        }
    }

    public void selectTabName(String tabName) {
        clickToElement(driver, dynamicTabNameByTxt, tabName);
    }

    public void selectFuncButonInQualifications(String btnName, String sectionName) {
        scrollToMiddle(driver, dynamicBtnUnderQualification, sectionName, btnName);
        clickToElement(driver, dynamicBtnUnderQualification, sectionName, btnName);
    }

    public void generateRandomStringWithLength(int length, String fieldName) {
        String randomString = DataGenerator.generateRandomString(length);
        String fieldByXpath = dynamicMyInfoFieldByTxt.replace("%s", fieldName);
        scrollToMiddle(driver, fieldByXpath);
        setText(driver, fieldByXpath, randomString);
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
        String fieldByXpath = dynamicMyInfoFieldByTxt.replace("%s", fieldName);
        scrollToMiddle(driver, fieldByXpath);
        setText(driver, fieldByXpath, randomText);
    }

    public void generateRandomStringWithLengthInTextArea(int length, String fieldName) {
        String randomString = DataGenerator.generateRandomString(length);
        String fieldByXpath = dynamicMyInfoFieldByTxtArea.replace("%s", fieldName);
        scrollToMiddle(driver, fieldByXpath);
        setText(driver, fieldByXpath, randomString);
    }

    public void updateTheSkillOnQualifications(String skillValue) {
        if (skillValue.equals("HRM_PROPERTY_RANDOM")) {
            Property property = DTOHolder.getInstance().getSkillProperty();
            skillValue = property.getPropertyValue();
        }
        selectFuncButonInQualifications("Add", "Skills");
        waitForLoadingIndicatorToDisappear(driver, eLoadingIndicator);
        String skillDdl = dynamicOptionByLabel.replace("%s", "Skill");
        scrollToMiddle(driver, skillDdl);
        selectItemInCustomDropdown(driver, skillDdl, skillOptionDdl, skillValue);
    }

    public void updateLanguage(String languageValue, String fluencyValue, String competencyValue) {
        if (languageValue.equals("HRM_PROPERTY_RANDOM")) {
            Property property = DTOHolder.getInstance().getLanguageProperty();
            languageValue = property.getPropertyValue();
        }
        selectFuncButonInQualifications("Add", "Languages");
        waitForLoadingIndicatorToDisappear(driver, eLoadingIndicator);
        String languageDdl = dynamicOptionByLabel.replace("%s", "Language");
        String fluencyDdl = dynamicOptionByLabel.replace("%s", "Fluency");
        String competencyDdl = dynamicOptionByLabel.replace("%s", "Competency");
        scrollToMiddle(driver, languageDdl);
        selectItemInCustomDropdown(driver, languageDdl, languageOptionDdl, languageValue);
        if (fluencyValue != null && !fluencyValue.isEmpty()) {
            scrollToMiddle(driver, fluencyDdl);
            selectItemInCustomDropdown(driver, fluencyDdl, fluencyOptionDdl, fluencyValue);
        }
        scrollToMiddle(driver, competencyDdl);
        selectItemInCustomDropdown(driver, competencyDdl, competencyOptionDdl, competencyValue);
    }
}