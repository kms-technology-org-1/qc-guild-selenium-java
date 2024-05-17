package org.kms.com.groupup04.pageobjects;

import org.kms.com.groupup04.utils.DataGenerator;

public class QualificationsEducationPage extends CommonPage{

    public String alertMsg = "//div[contains(@class, 'oxd-toast-content')]";

    public String btnAdd = "Add";
    public String btnSave = "Save";
    public String txtMainTitle = "Add Education";
    public String fieldEducationName = "Level";

    public void addNewEducation(String educationName) {
        clickToDynamicButtonByName(driver, btnAdd);
        verifyMainTitleIsDisplayed(txtMainTitle);
        inputValueIntoField(educationName, fieldEducationName);
        clickToDynamicButtonByName(driver, btnSave);
        sleepInSecond(1);
        verifyAlert(driver, alertMsg, "Successfully Saved");
    }
}
