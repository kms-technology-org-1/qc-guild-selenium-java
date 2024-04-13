package org.kms.com.groupup04.commons;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.kms.com.groupup04.pageobjects.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Arrays;

public class ChromeDriverFactory extends BasePage {
    public static ChromeOptions chromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("start-maximized");
        chromeOptions.addArguments("lang=pt-BR");
        chromeOptions.setCapability("chrome.switches", Arrays.asList("--ignore-certificate-errors,--web-security=false,--ssl-protocol=any,--ignore-ssl-errors=true"));
        chromeOptions.setAcceptInsecureCerts(true);
        if (headless.equals("true")) {
            chromeOptions.addArguments("--headless");
        }
        return chromeOptions;
    }

    public static WebDriver initDriver() {
        WebDriverManager.chromedriver().driverVersion("123.0.6312.05").setup();
        driver = new ChromeDriver(chromeOptions());
        return driver;
    }

}
