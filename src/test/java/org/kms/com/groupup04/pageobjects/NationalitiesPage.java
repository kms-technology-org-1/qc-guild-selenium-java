package org.kms.com.groupup04.pageobjects;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class NationalitiesPage extends CommonPage {

    public String alertMsg = "//div[contains(@class, 'oxd-toast-content')]";

    public String dynamicMainTitleTxt = "//h6[contains(@class, 'orangehrm-main-title')and(text()='%s')]";
    public String dynamicFieldByTxt = "//div[label[contains(., '%s')]]/following::input";
    public String dynamicNameRecordTxt = "//div[@class='oxd-table-body']//div[text()='%s']";

    public String btnAdd = "Add";
    public String btnSave = "Save";
    public String fieldNationalityName = "Name";
    public String txtMainTitle = "Add Nationality";
    public String navPaging = "//nav[@aria-label='Pagination Navigation']/ul/li";
    public String navNumber = navPaging + "/button[contains(@class,'oxd-pagination-page-item--page') and text()='%s']";
    public String dynamicBtnDeleteNationalityRecord = "//div[@role='rowgroup']//div[@class='oxd-table-card']//div[normalize-space(.)='%s']/..//i[contains(@class, 'bi-trash')]";

    public void addNewNationality(String nationalityName) {
        clickToDynamicButtonByName(driver, btnAdd);
        verifyMainTitleIsDisplayed(txtMainTitle);
        inputValueIntoField(nationalityName, fieldNationalityName);
        waitForLoadingIndicatorToDisappear(driver, eLoadingIndicator);
        clickToDynamicButtonByName(driver, btnSave);;
        verifyAlert(driver, alertMsg, "Successfully Saved");
    }

    //    public void verifyNameExistsOrNotInTable(String value, boolean isExists) {
//        boolean isActualDisplay = false;
//        String txtNameByValue = dynamicNameRecordTxt.replace("%s", value);
//        int numberOfRecordsFound = getNumberOfRecordsFound();
//        if (numberOfRecordsFound <= 50) {
//            isActualDisplay = elementIsExisted(txtNameByValue);
//        } else {
//            int numberPages = countNumberOfElementsByXPath(navPaging);
//            for (int index = 1; index < numberPages; index++) {
//                String navNum = navNumber.replace("%s", String.valueOf(index));
//                waitUntilElementIsClickable(driver, navNum);
//                sleepInSecond(2);
//                isActualDisplay = elementIsExisted(txtNameByValue);
//                if (isActualDisplay) {
//                    break;
//                }
//            }
//            Assert.assertEquals(isActualDisplay, isExists, "The expected nationality does not exist.");
//        }
////        Assert.assertEquals(isActualDisplay, isExists, "The expected nationality does not exist.");
//    }
    public void verifyNameExistsOrNotInTable(String value, boolean isExists) {
        boolean isActualDisplay = false;
        String txtNameByValue = dynamicNameRecordTxt.replace("%s", value);
        int numberOfRecordsFound = getNumberOfRecordsFound();
        if (numberOfRecordsFound <= 50) {
            isActualDisplay = elementIsExisted(txtNameByValue);
        } else {
            int numberPages = countNumberOfElementsByXPath(navPaging);
            for (int index = 1; index < numberPages; index++) {
                String navNum = navNumber.replace("%s", String.valueOf(index));
                scrollToMiddle(driver, navNum);
                WebElement element = waitUntilElementIsClickable(driver, navNum);
                clickToElement(element);
                sleepInSecond(2);
                isActualDisplay = elementIsExisted(txtNameByValue);
                if (isActualDisplay) {
                    break;
                }
            }
        }
        Assert.assertEquals(isActualDisplay, isExists, "The expected nationality does not exist.");
    }

    //    public void deleteNationalityRecord(String nationality, boolean isExists) {
//
//        boolean isActualDisplay = false;
//        String btnToDeleteNationalityRecord = dynamicBtnDeleteNationalityRecord.replace("%s", nationality);
//        int numberOfRecordsFound = getNumberOfRecordsFound();
//        if (numberOfRecordsFound <= 50) {
//            scrollToMiddle(driver, btnToDeleteNationalityRecord);
//            WebElement element = waitUntilElementIsClickable(driver, btnToDeleteNationalityRecord);
//            clickToElement(element);
//        } else {
//            int numberPages = countNumberOfElementsByXPath(navPaging);
//            for (int index = 1; index <= numberPages; index++) {
//                String navNum = navNumber.replace("%s", String.valueOf(index));
//                scrollToMiddle(driver, navNum);
//                WebElement elementPageNumber = waitUntilElementIsClickable(driver, navNum);
//                clickToElement(elementPageNumber);
//
//                waitForLoadingIndicatorToDisappear(driver, eLoadingIndicator);
//                scrollToMiddle(driver, btnToDeleteNationalityRecord);
//                WebElement eDeleteBtn = waitUntilElementIsClickable(driver, btnToDeleteNationalityRecord);
//                clickToElement(eDeleteBtn);
//                isActualDisplay = elementIsExisted(btnToDeleteNationalityRecord);
//                if (isActualDisplay) {
//                    break;
//                }
//            }
//        }
//    }
    public void deleteNationalityRecord(String nationality) {
        boolean isActualDisplay = false;
        String btnToDeleteNationalityRecord = dynamicBtnDeleteNationalityRecord.replace("%s", nationality);
        int numberOfRecordsFound = getNumberOfRecordsFound();

        if (numberOfRecordsFound <= 50) {
            scrollToMiddle(driver, btnToDeleteNationalityRecord);
            WebElement element = waitUntilElementIsClickable(driver, btnToDeleteNationalityRecord);
            clickToElement(element);
        } else {
            int numberPages = countNumberOfElementsByXPath(navPaging);
            for (int index = 1; index < numberPages; index++) {
                String navNum = navNumber.replace("%s", String.valueOf(index));
                scrollToMiddle(driver, navNum);
                WebElement element = waitUntilElementIsClickable(driver, navNum);
                clickToElement(element);
                sleepInSecond(2);
                isActualDisplay = elementIsExisted(btnToDeleteNationalityRecord);
                if (isActualDisplay) {
                    scrollToMiddle(driver, btnToDeleteNationalityRecord);
                    WebElement element2 = waitUntilElementIsClickable(driver, btnToDeleteNationalityRecord);
                    clickToElement(element2);
                    break;
                }
            }
        }
    }


//    public void verifyMainTitleIsDisplayed(String expectedMainTitle){
//        CommonPage.waitForElementVisible(driver, dynamicMainTitleTxt, expectedMainTitle);
//        CommonPage.verifyElementIsDisplayed(Duration.ofSeconds(AppConfigs.TIMEOUT_MEDIUM), dynamicMainTitleTxt, expectedMainTitle);
//    }
//
//    public void inputValueIntoField(String value, String fieldName){
//        CommonPage.waitForElementVisible(driver, dynamicFieldByTxt, fieldName);
//        CommonPage.setText(driver, value, dynamicFieldByTxt, fieldName);
//    }


}

