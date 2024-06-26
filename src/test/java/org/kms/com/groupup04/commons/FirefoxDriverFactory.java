package org.kms.com.groupup04.commons;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.kms.com.groupup04.pageobjects.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxDriverFactory extends BasePage {
    public static FirefoxOptions firefoxOptions() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--disable-gpu");
        firefoxOptions.addArguments("start-maximized");
        firefoxOptions.addArguments("lang=pt-BR");
        firefoxOptions.setAcceptInsecureCerts(true);
        return firefoxOptions;
    }

    public static WebDriver initDriver() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver(firefoxOptions());
        return driver;
    }
}
