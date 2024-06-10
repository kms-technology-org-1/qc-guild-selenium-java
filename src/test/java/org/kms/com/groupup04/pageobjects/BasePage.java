package org.kms.com.groupup04.pageobjects;

import org.kms.com.groupup04.utils.ConfigReader;
import org.openqa.selenium.WebDriver;

public class BasePage {
    public static WebDriver driver;
    public static String env = System.getProperty("env") == null ? "qa" : System.getProperty("env");
    public static String browser = System.getProperty("browser") == null ? "chrome" : System.getProperty("browser");
    public static ConfigReader config = new ConfigReader("src/test/resources/configs/" + env + ".properties");
    public static String dataFilePath = "src/test/java/org/kms/com/groupup04/data/dataFile/";
}