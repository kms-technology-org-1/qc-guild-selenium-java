package org.kms.com.groupup04.pageobjects;

import org.kms.com.groupup04.commons.DTOHolder;
import org.kms.com.groupup04.data.dto.EmployeeInfo;
import org.kms.com.groupup04.utils.DataGenerator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Random;
import java.security.SecureRandom;

public class ChangePasswordPage extends CommonPage {
    public static EmployeeInfo employeeInfo;
    public String newPassword;

    @FindBy(xpath = "//div[@class='oxd-topbar-header-userarea']//li[contains(@class, 'oxd-userdropdown')]")
    public WebElement eUserProfileDdl;

    @FindBy(xpath = "//ul[@role='menu']//a[text()='Change Password']")
    public WebElement eChangePasswordDdo;

    public String dynamicUserDetailTxt = "//label[text()='%s']//parent::div/following-sibling::div/input";
    public String alertMsg = "//div[contains(@class, 'oxd-toast-content')]";

    public void clickOnchangePasswordInUserProfile() {
        waitForElementVisible(driver, eUserProfileDdl);
        clickToElement(eUserProfileDdl);
        waitForElementVisible(driver, eChangePasswordDdo);
        clickToElement(eChangePasswordDdo);
    }

    public void generatePasswordWithLength(int passwordLength) {
        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String number = "0123456789";
        String symbol = "@#$%^&*()_+~|}{[]></-=";

        String passwordFormat = upperCase + lowerCase + number + symbol;

        Random random = new SecureRandom();

        String password = generateStringOfCharacters(1, upperCase, random) +
                generateStringOfCharacters(1, lowerCase, random) +
                generateStringOfCharacters(1, number, random) +
                generateStringOfCharacters(1, symbol, random) +
                generateStringOfCharacters(passwordLength - 4, passwordFormat, random);

        newPassword = password;
    }

    public String generateStringOfCharacters(int length, String charString, Random random) {
        StringBuilder result = new StringBuilder();

        while (length-- > 0) {
            int index = random.nextInt(charString.length());
            result.append(charString.charAt(index));
        }

        return result.toString();
    }

    public void inputValueIntoField(String value, String fieldName) {
        String newValue = "";
        EmployeeInfo employeeInfo = DTOHolder.getInstance().getEmployeeInfoDTO();
        if (value.equals("current password")) {
            newValue = employeeInfo.getPassword();
        } else if (value.equals("random password")) {
            newValue = DataGenerator.generateRandomString(10);
        } else if (value.equals("empty")) {
            newValue = "";
        } else {
            newValue = newPassword;
            employeeInfo.setPassword(newPassword);
            DTOHolder.getInstance().setEmployeeInfoDTO(employeeInfo);
        }
        CommonPage.setText(driver, newValue, dynamicUserDetailTxt, fieldName);
    }

    public void generatePwdWithLengthAndCondition(int passwordLength, String condition) {
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
                password = generateStringOfCharacters(1, upperCase, random) +
                        generateStringOfCharacters(1, lowerCase, random) +
                        generateStringOfCharacters(1, number, random) +
                        generateStringOfCharacters(passwordLength - 3, passwordFormat, random);

                newPassword = password;
                break;
            case "no lower-case letter":
                passwordFormat = upperCase + number + symbol;
                password = generateStringOfCharacters(1, upperCase, random) +
                        generateStringOfCharacters(1, number, random) +
                        generateStringOfCharacters(1, symbol, random) +
                        generateStringOfCharacters(passwordLength - 3, passwordFormat, random);
                newPassword = password;
                break;
            case "no upper-case letter":
                passwordFormat = lowerCase + number + symbol;
                password = generateStringOfCharacters(1, lowerCase, random) +
                        generateStringOfCharacters(1, number, random) +
                        generateStringOfCharacters(1, symbol, random) +
                        generateStringOfCharacters(passwordLength - 3, passwordFormat, random);
                newPassword = password;
                break;
            case "no digit":
                passwordFormat = upperCase + lowerCase + symbol;
                password = generateStringOfCharacters(1, upperCase, random) +
                        generateStringOfCharacters(1, lowerCase, random) +
                        generateStringOfCharacters(1, symbol, random) +
                        generateStringOfCharacters(passwordLength - 3, passwordFormat, random);
                newPassword = password;
                break;
            case "contain space":
                passwordFormat = upperCase + lowerCase + number + symbol;
                password = generateStringOfCharacters(1, upperCase, random) +
                        generateStringOfCharacters(1, lowerCase, random) +
                        generateStringOfCharacters(1, number, random) +
                        generateStringOfCharacters(1, symbol, random) + " " +
                        generateStringOfCharacters(passwordLength - 4, passwordFormat, random);
                newPassword = password;

                break;
            case "full Option":
                password = generateStringOfCharacters(1, upperCase, random) +
                        generateStringOfCharacters(1, lowerCase, random) +
                        generateStringOfCharacters(1, number, random) +
                        generateStringOfCharacters(1, symbol, random) +
                        generateStringOfCharacters(passwordLength - 4, passwordFormat, random);
                newPassword = password;
                break;
        }
    }

    public void verifyAlertMsg(String msg) {
        verifyAlert(driver, alertMsg, msg);
    }
}


