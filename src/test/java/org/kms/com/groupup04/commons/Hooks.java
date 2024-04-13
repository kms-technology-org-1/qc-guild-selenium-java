package org.kms.com.groupup04.commons;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.kms.com.groupup04.pageobjects.BasePage;
import org.kms.com.groupup04.utils.ConfigReader;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

import static org.kms.com.groupup04.utils.AppConfigs.*;

public class Hooks extends BasePage {
	protected ConfigReader config, browser_version;
	protected String env = System.getProperty("env") == null ? "qa" : System.getProperty("env");
	protected String browser = System.getProperty("browser") == null ? "chrome" : System.getProperty("browser");
	protected String headless = System.getProperty("headless") == null ? "false" : System.getProperty("headless");

	@Before
	public void setupDriver() {
		config = new ConfigReader("src/test/resources/configs/" + env + ".properties");
		browser_version = new ConfigReader("src/test/resources/configs/browser_version.properties");
		System.out.println("Running on '" + env.toUpperCase() + "' environment with '" + browser.toUpperCase() + "' browser with version is " + browser_version.getProperty(browser) + ".");
		switch (browser){
			case "chrome":
				WebDriverManager.chromedriver().setup();
//				WebDriverManager.chromedriver().browserVersion(browser_version.getProperty(browser)).setup();
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--headless");
				chromeOptions.addArguments("--disable-gpu");
				if (headless.equals("true")) {
					driver = new ChromeDriver(chromeOptions);
				} else {
					driver = new ChromeDriver();
				}
				break;
			case "firefox":
				WebDriverManager.firefoxdriver().setup();
//				WebDriverManager.firefoxdriver().browserVersion(browser_version.getProperty(browser)).setup();
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				firefoxOptions.addArguments("--headless");
				firefoxOptions.addArguments("--disable-gpu");
				if (headless.equals("true")) {
					driver = new FirefoxDriver(firefoxOptions);
				} else {
					driver = new FirefoxDriver();
				}
				break;
			case "edge":
				WebDriverManager.edgedriver().setup();
//				WebDriverManager.edgedriver().browserVersion(browser_version.getProperty(browser)).setup();
				EdgeOptions edgeOptions = new EdgeOptions();
				edgeOptions.addArguments("--headless");
				edgeOptions.addArguments("--disable-gpu");
				if (headless.equals("true")) {
					driver = new EdgeDriver(edgeOptions);
				} else {
					driver = new EdgeDriver();
				}
				break;
			default:
				System.out.println("Not support to run tests on" + browser.toUpperCase() + " browser.");
				break;
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT_LARGE));
		driver.get(config.getProperty("base_url"));
	}

	@After(order=0)
	public void quitDriver() {
		driver.quit();
	}

	@After(order = 1)
	public void takeScreenShotOnFailedScenario(Scenario scenario) {
		if ((scenario.isFailed())) {
			System.out.println("Test failed. Capturing screenshot...");
			final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", scenario.getName());
		}
	}
}
