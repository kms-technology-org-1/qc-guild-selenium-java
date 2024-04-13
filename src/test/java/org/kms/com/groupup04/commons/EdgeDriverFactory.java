package org.kms.com.groupup04.commons;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.kms.com.groupup04.pageobjects.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class EdgeDriverFactory extends BasePage {
    public static WebDriver initDriver() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        return driver;
    }
}
