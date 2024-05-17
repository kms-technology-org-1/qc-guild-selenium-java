package org.kms.com.groupup04.pageobjects;

import com.google.gson.Gson;
import org.kms.com.groupup04.commons.DTOHolder;
import org.kms.com.groupup04.data.dto.EmployeeInfo;
import org.kms.com.groupup04.utils.DataGenerator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDateTime;

public class AddEmployeePage extends CommonPage {


    public static EmployeeInfo employeeInfo;

    @FindBy(xpath = "//input[@name='firstName']")
    public WebElement eFirstNameTextbox;

    @FindBy(xpath = "//input[@name='middleName']")
    public WebElement eMiddleNameTextbox;

    @FindBy(xpath = "//input[@name='lastName']")
    public WebElement eLastNameTextbox;

    @FindBy(xpath = "//div[label[contains(., 'Employee Id')]]/following::input[contains(@class, 'oxd-input')]")
    public WebElement eEmployeeIdTextbox;

    @FindBy(xpath = "//div[label[contains(., 'Username')]]/following::input")
    public WebElement eUserNameTextbox;

    @FindBy(xpath = "//div[label[contains(., 'Password')]]/following::input")
    public WebElement ePwdTextbox;

    @FindBy(xpath = "//div[label[contains(., 'Confirm Password')]]/following::input")
    public WebElement eConfirmPwdTextbox;

    @FindBy(xpath = "//div[@class='oxd-switch-wrapper']//span")
    public WebElement eCreateLoginToggle;

    @FindBy(xpath = "//div[@class='oxd-loading-spinner']")
    public WebElement eLoadingIndicator;

    public String alertMsg = "//div[contains(@class, 'oxd-toast-content')]";

    public String pagePIM = "PIM";
    public String btnAdd = "Add";
    public String txtAddEmployeeTitle = "Add Employee";
    public String alertSaveSuccessfully = "Successfully Saved";
    public String btnSave = "Save";

    public void clickToCreateLoginToggle() {
        clickToElement(eCreateLoginToggle);
    }

    public void createNewEmployee() throws FileNotFoundException {
        long currentDateTime = LocalDateTime.now().toEpochSecond(java.time.ZoneOffset.UTC);

        Gson gson = new Gson();

        employeeInfo = gson.fromJson(new FileReader(dataFilePath + "EmployeeInfo.json"), EmployeeInfo.class);

        String firstName = employeeInfo.getFirstName();
        String middleName = employeeInfo.getMiddleName();
        String lastName = employeeInfo.getLastName();
        String username = employeeInfo.getUsername() + currentDateTime;
        String password = employeeInfo.getPassword() + DataGenerator.generateRandomNumber();

        employeeInfo.setUsername(username);
        employeeInfo.setPassword(password);
        DTOHolder.getInstance().setEmployeeInfoDTO(employeeInfo);

        waitForElementVisible(driver, eFirstNameTextbox);
        setText(eFirstNameTextbox, firstName);
        setText(eMiddleNameTextbox, middleName);
        setText(eLastNameTextbox, lastName);
        clickToCreateLoginToggle();
        setText(eUserNameTextbox, username);
        setText(ePwdTextbox, password);
        setText(eConfirmPwdTextbox, password);
        waitForLoadingIndicatorToDisappear(driver, eLoadingIndicator);
        clickToDynamicButtonByName(driver, btnSave);
    }

    public void verifyAlertSaveSuccessfully() {
        verifyAlert(driver, alertMsg, alertSaveSuccessfully);
    }

    public void goToAddEmployeePage() {
        clickToMainMenuItem(pagePIM);
        clickToDynamicButtonByName(driver, btnAdd);
        waitForLoadingIndicatorToDisappear(driver, eLoadingIndicator);
        verifyMainTitle(txtAddEmployeeTitle);
    }
}
