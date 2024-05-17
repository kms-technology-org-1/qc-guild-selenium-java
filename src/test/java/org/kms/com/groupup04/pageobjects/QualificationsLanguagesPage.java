package org.kms.com.groupup04.pageobjects;

import org.kms.com.groupup04.utils.DataGenerator;

public class QualificationsLanguagesPage extends CommonPage{

    public String alertMsg = "//div[contains(@class, 'oxd-toast-content')]";

    public String btnAdd = "Add";
    public String btnSave = "Save";
    public String txtMainTitle = "Add Language";
    public String fieldLanguagesName = "Name";

    public void addNewLanguages(String languageName) {
        clickToDynamicButtonByName(driver, btnAdd);
        verifyMainTitleIsDisplayed(txtMainTitle);
        inputValueIntoField(languageName, fieldLanguagesName);
        clickToDynamicButtonByName(driver, btnSave);
        sleepInSecond(1);
        verifyAlert(driver, alertMsg, "Successfully Saved");
    }
}
