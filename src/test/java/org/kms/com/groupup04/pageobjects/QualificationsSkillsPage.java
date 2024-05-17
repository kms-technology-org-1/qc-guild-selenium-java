package org.kms.com.groupup04.pageobjects;

import org.kms.com.groupup04.utils.DataGenerator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class QualificationsSkillsPage extends CommonPage{

    @FindBy(xpath = "//label[normalize-space(.)='Description']/../..//textarea")
    public static WebElement eDescriptionField;

    public String alertMsg = "//div[contains(@class, 'oxd-toast-content')]";

    public String btnAdd = "Add";
    public String btnSave = "Save";
    public String txtMainTitle = "Add Skill";
    public String fieldEducationName = "Name";

    public void addNewSkill(String skillName, String skillDescription) {
        clickToDynamicButtonByName(driver, btnAdd);
        verifyMainTitleIsDisplayed(txtMainTitle);
        inputValueIntoField(skillName, fieldEducationName);
        inputDescriptionToSkill(skillDescription);
        clickToDynamicButtonByName(driver, btnSave);
        sleepInSecond(1);
        verifyAlert(driver, alertMsg, "Successfully Saved");
    }

    public void inputDescriptionToSkill(String txtDescription){
        waitForElementVisible(driver, eDescriptionField);
        setText(eDescriptionField,txtDescription);
    }
}
