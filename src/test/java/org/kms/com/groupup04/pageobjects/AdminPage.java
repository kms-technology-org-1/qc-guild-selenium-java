package org.kms.com.groupup04.pageobjects;

import org.kms.com.groupup04.commons.CustomException;
import org.kms.com.groupup04.commons.DTOHolder;
import org.kms.com.groupup04.data.dto.Property;
import org.kms.com.groupup04.utils.DataGenerator;

import java.time.LocalDateTime;

public class AdminPage extends CommonPage {
    public Property property = new Property();

    public String alertMsg = "//div[contains(@class, 'oxd-toast-content')]";

    long currentDateTime = LocalDateTime.now().toEpochSecond(java.time.ZoneOffset.UTC);

    public void addNewPropertyInItemNameUnderQualifications(String propertyName, String value) throws CustomException {
        String propertyValue = "";
        if (value.equals("HRM_PROPERTY_RANDOM")) {
            propertyValue = "Testing Property " + DataGenerator.generateRandomString(5) + " " + currentDateTime;
        } else {
            propertyValue = value;
        }
        property.setPropertyValue(propertyValue);

        switch (propertyName.toLowerCase()) {
            case "skills":
                DTOHolder.getInstance().setSkillProperty(property);
                break;
            case "languages":
                DTOHolder.getInstance().setLanguageProperty(property);
                break;
            default:
                throw new CustomException("The Property is not suitable.");
        }

        selectDropdownMenuItemByText("Qualifications", propertyName);
        clickToDynamicButtonByName(driver, "Add");
        inputValueIntoField(propertyValue, "Name");
        clickToDynamicButtonByName(driver, "Save");
        verifyAlert(driver, alertMsg, "Successfully Saved");
        waitForLoadingIndicatorToDisappear(driver, eLoadingIndicator);
        verifyRecordWithTitleNameIsDisplayed(propertyName, propertyValue);
    }

    public void deleteRecordByNameInQualifications(String propertyName, String propertyValue) throws CustomException {
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
                    throw new CustomException("The Property is not suitable.");
            }
        }
        waitForLoadingIndicatorToDisappear(driver, eLoadingIndicator);
        String btnToDeleteRecord = dynamicBtnDeletePropertyRecord.replace("%s", propertyValue);
        scrollToMiddle(driver, btnToDeleteRecord);
        clickToElement(driver, btnToDeleteRecord);
    }
}