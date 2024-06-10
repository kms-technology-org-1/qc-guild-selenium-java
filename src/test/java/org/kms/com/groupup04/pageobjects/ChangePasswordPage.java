package org.kms.com.groupup04.pageobjects;

import org.kms.com.groupup04.commons.CustomException;
import org.kms.com.groupup04.commons.DTOHolder;
import org.kms.com.groupup04.data.dto.EmployeeInfo;
import org.kms.com.groupup04.utils.DataGenerator;

import java.security.SecureRandom;
import java.util.Random;

public class ChangePasswordPage extends CommonPage {
    public String newPassword;

    public String ddlUserProfile = "//div[@class='oxd-topbar-header-userarea']//li[contains(@class, 'oxd-userdropdown')]";
    public String ddoChangePassword = "//ul[@role='menu']//a[text()='Change Password']";

    public String dynamicUserDetailTxt = "//label[text()='%s']//parent::div/following-sibling::div/input";


    public void clickChangePasswordInUserProfile() {
        clickToElement(driver, ddlUserProfile);
        clickToElement(driver, ddoChangePassword);
    }

    public void inputPasswordIntoField(String value, String fieldName) {
        String newValue = "";
        EmployeeInfo employeeInfo = DTOHolder.getInstance().getEmployeeInfoDTO();
        if (fieldName.toLowerCase().contains("password")) {
            if (value.equals("HRM_EMPLOYEE_PASSWORD")) {
                newValue = employeeInfo.getPassword();
            } else if (value.equals("HRM_EMPLOYEE_PASSWORD_RANDOMLY")) {
                newValue = newPassword;
                employeeInfo.setPassword(newValue);
                DTOHolder.getInstance().setEmployeeInfoDTO(employeeInfo);
            }
        } else {
            newValue = value;
        }
        setText(driver, newValue, dynamicUserDetailTxt, fieldName);
    }

    public void generatePwdWithLengthAndCondition(int passwordLength, String condition) throws CustomException {
        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String number = "0123456789";
        String symbol = "@#$%^&*()_+~|}{[]></-=";
        String password = "";
        String passwordFormat = upperCase + lowerCase + number + symbol;

        Random random = new SecureRandom();

        switch (condition) {
            case "no special character":
                passwordFormat = upperCase + lowerCase + number;
                password = DataGenerator.generateStringOfCharacters(1, upperCase, random) +
                        DataGenerator.generateStringOfCharacters(1, lowerCase, random) +
                        DataGenerator.generateStringOfCharacters(1, number, random) +
                        DataGenerator.generateStringOfCharacters(passwordLength - 3, passwordFormat, random);

                newPassword = password;
                break;
            case "no lower-case letter":
                passwordFormat = upperCase + number + symbol;
                password = DataGenerator.generateStringOfCharacters(1, upperCase, random) +
                        DataGenerator.generateStringOfCharacters(1, number, random) +
                        DataGenerator.generateStringOfCharacters(1, symbol, random) +
                        DataGenerator.generateStringOfCharacters(passwordLength - 3, passwordFormat, random);
                newPassword = password;
                break;
            case "no upper-case letter":
                passwordFormat = lowerCase + number + symbol;
                password = DataGenerator.generateStringOfCharacters(1, lowerCase, random) +
                        DataGenerator.generateStringOfCharacters(1, number, random) +
                        DataGenerator.generateStringOfCharacters(1, symbol, random) +
                        DataGenerator.generateStringOfCharacters(passwordLength - 3, passwordFormat, random);
                newPassword = password;
                break;
            case "no digit":
                passwordFormat = upperCase + lowerCase + symbol;
                password = DataGenerator.generateStringOfCharacters(1, upperCase, random) +
                        DataGenerator.generateStringOfCharacters(1, lowerCase, random) +
                        DataGenerator.generateStringOfCharacters(1, symbol, random) +
                        DataGenerator.generateStringOfCharacters(passwordLength - 3, passwordFormat, random);
                newPassword = password;
                break;
            case "contain space":
                passwordFormat = upperCase + lowerCase + number + symbol;
                password = DataGenerator.generateStringOfCharacters(1, upperCase, random) +
                        DataGenerator.generateStringOfCharacters(1, lowerCase, random) +
                        DataGenerator.generateStringOfCharacters(1, number, random) +
                        DataGenerator.generateStringOfCharacters(1, symbol, random) + " " +
                        DataGenerator.generateStringOfCharacters(passwordLength - 4, passwordFormat, random);
                newPassword = password;

                break;
            case "full options":
                password = DataGenerator.generateStringOfCharacters(1, upperCase, random) +
                        DataGenerator.generateStringOfCharacters(1, lowerCase, random) +
                        DataGenerator.generateStringOfCharacters(1, number, random) +
                        DataGenerator.generateStringOfCharacters(1, symbol, random) +
                        DataGenerator.generateStringOfCharacters(passwordLength - 4, passwordFormat, random);
                newPassword = password;
                break;
            default:
                throw new CustomException("The password does not meet the required conditions.");
        }
    }
}