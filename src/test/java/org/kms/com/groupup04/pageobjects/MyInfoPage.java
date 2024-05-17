package org.kms.com.groupup04.pageobjects;

import org.kms.com.groupup04.utils.AppConfigs;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MyInfoPage extends CommonPage{

    public String nationalityDdl = "//div[contains(@class, 'oxd-grid-item') and .//label[normalize-space(text())='Nationality']]//i[contains(@class, 'oxd-select-text--arrow')]";

    public String nationalityOptionDdl = "//div[contains(@class, 'oxd-grid-item') and .//label[normalize-space(text())='Nationality']]//div[@role='listbox']//div/span";
    public String educationOptionDdl = "//div[contains(@class, 'oxd-grid-item') and .//label[normalize-space(text())='Level']]//div[@role='listbox']//div[@role='option']/span";
    public String skillOptionDdl = "//div[contains(@class, 'oxd-grid-item') and .//label[normalize-space(text())='Skill']]//div[@role='listbox']//div[@role='option']/span";
    public String languageOptionDdl = "//div[contains(@class, 'oxd-grid-item') and .//label[normalize-space(text())='Language']]//div[@role='listbox']//div[@role='option']/span";
    public String fluencyOptionDdl = "//div[contains(@class, 'oxd-grid-item') and .//label[normalize-space(text())='Fluency']]//div[@role='listbox']//div[@role='option']/span";
    public String licenseOptionDdl = "//div[contains(@class, 'oxd-grid-item') and .//label[normalize-space(text())='License Type']]//div[@role='listbox']//div[@role='option']/span";
    public String competencyOptionDdl = "//div[contains(@class, 'oxd-grid-item') and .//label[normalize-space(text())='Competency']]//div[@role='listbox']//div[@role='option']/span";
    public String dynamicTabNameByTxt = "//div[@role='tablist']//a[normalize-space(.) = '%s']";
    public String dynamicBtnUnderQualificationSection = "//h6[normalize-space(.) = '%s']/following-sibling::button[normalize-space(.) = '%s']";
    public String dynamicOptionByLabel = "//div[contains(concat(' ', @class, ' '), ' oxd-input-group ')and .//label[normalize-space(text()) = '%s']]//i";

    @FindBy(xpath = "//div[@class='oxd-select-dropdown --position-bottom' and role='listbox']]//div/span")
    public static WebElement eNationalityDdo;

    public void updateNationality(String nationalityName) {
        waitForLoadingIndicatorToDisappear(driver, eLoadingIndicator);
        selectItemInCustomDropdown(driver, nationalityDdl, nationalityOptionDdl, nationalityName);
    }

    public void updateEducation(String educationName) {
        selectFuncButtonUnderQualification("Education", "Add");
        String educationDdl = dynamicOptionByLabel.replace("%s", "Level");
//        scrollToElementOnDown(driver, educationDdl);
        selectItemInCustomDropdown(driver, educationDdl, educationOptionDdl, educationName);
    }

    public void updateSkill(String skillOption) {
        selectFuncButtonUnderQualification("Skills", "Add");
        waitForLoadingIndicatorToDisappear(driver, eLoadingIndicator);
        String skillDdl = dynamicOptionByLabel.replace("%s", "Skill");
        scrollToMiddle(driver, skillDdl);
        selectItemInCustomDropdown(driver, skillDdl, skillOptionDdl, skillOption);
    }

    public void updateLanguage(String languageOption, String fluencyOption, String competencyOption) {
        selectFuncButtonUnderQualification("Languages", "Add");
        waitForLoadingIndicatorToDisappear(driver, eLoadingIndicator);
        String languageDdl = dynamicOptionByLabel.replace("%s", "Language");
        String fluencyDdl = dynamicOptionByLabel.replace("%s", "Fluency");
        String competencyDdl = dynamicOptionByLabel.replace("%s", "Competency");
        scrollToMiddle(driver, languageDdl);
        selectItemInCustomDropdown(driver, languageDdl, languageOptionDdl, languageOption);
        scrollToMiddle(driver, fluencyDdl);
        selectItemInCustomDropdown(driver, fluencyDdl, fluencyOptionDdl, fluencyOption);
        scrollToMiddle(driver, competencyDdl);
        selectItemInCustomDropdown(driver, competencyDdl, competencyOptionDdl, competencyOption);
    }

    public void updateLicense(String licenseOption) {
        selectFuncButtonUnderQualification("License", "Add");
        waitForLoadingIndicatorToDisappear(driver, eLoadingIndicator);
        String licenseDdl = dynamicOptionByLabel.replace("%s", "License Type");
        scrollToMiddle(driver, licenseDdl);
        selectItemInCustomDropdown(driver, licenseDdl, licenseOptionDdl, licenseOption);
    }

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

    public void selectFuncButtonUnderQualification(String sectionName, String btnName){
        clickToElement(driver, dynamicBtnUnderQualificationSection, sectionName, btnName);

    }


}
