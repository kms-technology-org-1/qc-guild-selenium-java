package org.kms.com.groupup04.pageobjects;

import org.kms.com.groupup04.utils.DataGenerator;

public class QualificationsLicensesPage extends CommonPage{

    public String alertMsg = "//div[contains(@class, 'oxd-toast-content')]";

    public String btnAdd = "Add";
    public String btnSave = "Save";
    public String txtMainTitle = "Add License";
    public String fieldLicensesName = "Name";

    public void addNewLicenses(String licenseName) {
        clickToDynamicButtonByName(driver, btnAdd);
        verifyMainTitleIsDisplayed(txtMainTitle);
        inputValueIntoField(licenseName, fieldLicensesName);
        clickToDynamicButtonByName(driver, btnSave);
        sleepInSecond(1);
        verifyAlert(driver, alertMsg, "Successfully Saved");
    }
}
