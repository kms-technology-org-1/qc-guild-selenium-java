package org.kms.com.groupup04.pageobjects;

import org.kms.com.groupup04.commons.CommonConstants;
import org.kms.com.groupup04.commons.CustomException;
import org.kms.com.groupup04.commons.DTOHolder;
import org.kms.com.groupup04.data.dto.Property;
import org.kms.com.groupup04.utils.DataGenerator;

import java.time.LocalDateTime;

public class AdminPage extends CommonPage {
    public Property property = new Property();

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

        selectDropdownMenuItemByText(CommonConstants.QUALIFICATIONS, propertyName);
        clickToDynamicButtonByName(driver, CommonConstants.ADD);
        inputValueIntoField(propertyValue, CommonConstants.NAME);
        clickToDynamicButtonByName(driver, CommonConstants.SAVE);
    }
}