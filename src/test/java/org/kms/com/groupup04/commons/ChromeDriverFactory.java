package org.kms.com.groupup04.commons;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.kms.com.groupup04.pageobjects.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverFactory extends BasePage {
    public static ChromeOptions chromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("start-maximized");
        chromeOptions.addArguments("lang=pt-BR");
        chromeOptions.setAcceptInsecureCerts(true);
        return chromeOptions;
    }

    public static WebDriver initDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(chromeOptions());
        return driver;
    }
}
