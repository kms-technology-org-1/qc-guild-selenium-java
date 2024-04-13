package org.kms.com.groupup04.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = "classpath:features",
		glue = { "org.kms.com.groupup04.commons", "org.kms.com.groupup04.stepdefinitions", "org.kms.com.groupup04.runner" },
		plugin = { "pretty", "json:target/cucumber-reports/cucumber.json", "html:target/cucumber-reports/cucumberreport.html",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
		monochrome = true)
public class TestRunner extends AbstractTestNGCucumberTests {

}
