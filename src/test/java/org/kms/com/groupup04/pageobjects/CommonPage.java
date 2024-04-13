package org.kms.com.groupup04.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class CommonPage {

    final WebDriver driver;

    public CommonPage(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyPageTitle(String expectedTitle){
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    public static void setText(WebElement element, String text){
        element.clear();
        element.sendKeys(text);
    }
}
