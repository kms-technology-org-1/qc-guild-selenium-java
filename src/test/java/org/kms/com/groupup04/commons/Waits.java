package org.kms.com.groupup04.commons;

import org.kms.com.groupup04.utils.AppConfigs;
import org.kms.com.groupup04.utils.StringUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waits {

    public static WebElement waitForElementClickable(WebDriver driver, String xpath, String... dynamicValues) {
        String dynamicLocator = StringUtil.castRestParameter(xpath, dynamicValues);
        return new WebDriverWait(driver, Duration.ofSeconds(AppConfigs.TIMEOUT_SMALL)).until(ExpectedConditions.elementToBeClickable(By.xpath(dynamicLocator)));
    }

    public static void waitForElementVisible(WebDriver driver, WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(AppConfigs.TIMEOUT_MEDIUM))
                .until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitForElementVisible(WebDriver driver, String xpath, String... dynamicValues) {
        String dynamicLocator = StringUtil.castRestParameter(xpath, dynamicValues);
        return new WebDriverWait(driver, Duration.ofSeconds(AppConfigs.TIMEOUT_SMALL)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dynamicLocator)));
    }

    public static void waitForLoadingIndicatorToDisappear(WebDriver driver, WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(AppConfigs.TIMEOUT_SMALL)).until(ExpectedConditions.invisibilityOf(element));
    }
}