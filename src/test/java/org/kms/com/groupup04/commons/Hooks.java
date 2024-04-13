package org.kms.com.groupup04.commons;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.kms.com.groupup04.pageobjects.BasePage;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.time.Duration;
import java.time.temporal.TemporalUnit;
import java.util.concurrent.TimeUnit;

import static org.kms.com.groupup04.utils.AppConfigs.*;

public class Hooks extends BasePage {

	@Before
	public void setupDriver() {
		System.out.println("Running on '" + env.toUpperCase() + "' environment with '" + browser.toUpperCase() + "' browser.");
		switch (browser){
			case "chrome":
				driver = ChromeDriverFactory.initDriver();
				break;
			case "firefox":
				driver = new FirefoxDriverFactory().initDriver();
				break;
			case "edge":
				driver = new EdgeDriverFactory().initDriver();
				break;
			default:
				System.out.println("Not support to run tests on" + browser.toUpperCase() + " browser.");
				break;
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT_LARGE));
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
