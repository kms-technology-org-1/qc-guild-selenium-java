package org.kms.com.groupup04.stepdefinitions;

import io.cucumber.java.en.When;
import org.kms.com.groupup04.pageobjects.AddEmployeePage;
import org.kms.com.groupup04.pageobjects.BasePage;
import org.openqa.selenium.support.PageFactory;

import java.io.FileNotFoundException;

public class AddEmployeePageSteps extends BasePage {

    AddEmployeePage addEmployeePage = PageFactory.initElements(driver, AddEmployeePage.class);

    @When("user creates an ESS account")
    public void createESSAccount() throws FileNotFoundException {
        addEmployeePage.goToAddEmployeePage();
        addEmployeePage.createNewEmployee();
    }
}