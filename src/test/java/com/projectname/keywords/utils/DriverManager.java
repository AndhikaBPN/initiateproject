package com.projectname.keywords.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DriverManager {

    private static WebDriver driver;

    static WebDriver getDriver(Platform platform, String url) {
        switch (platform) {
            case CHROME:
                driver = chromeDriver(url);
                break;
            case CHROME_HEADLESS:
                driver = chromeDriverHeadless(url);
                break;
            case FIREFOX:
                driver = firefoxDriver(url);
                break;
            case FIREFOX_HEADLESS:
                driver = firefoxDriverHeadless(url);
                break;
            case EDGE:
                driver = edgeDriver(url);
                break;
            case EDGE_HEADLESS:
                driver = edgeDriverHeadless(url);
                break;
            case SAFARI:
                driver = safariDriver(url);
                break;
            default:
                driver = chromeDriver(url);
                break;
        }

        return driver;

    }

    static WebDriver getWebDriver() {
        return driver;
    }

    private static WebDriver chromeDriver(String url) {
        TestLogger.logInfo("Redirect to URL: " + url);

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();

        Utils.delay(3);

        return driver;
    }

    private static WebDriver chromeDriverHeadless(String url) {
        TestLogger.logInfo("Redirect to URL: " + url);

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Run in headless mode
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(options);
        driver.get(url);
        driver.manage().window().maximize();

        Utils.delay(3);

        return driver;
    }

    private static WebDriver firefoxDriver(String url) {
        TestLogger.logInfo("Redirect to URL: " + url);

        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        driver.get(url);
        driver.manage().window().maximize();

        Utils.delay(3);

        return driver;
    }

    private static WebDriver firefoxDriverHeadless(String url) {
        TestLogger.logInfo("Redirect to URL: " + url);

        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless"); // Run in headless mode
        WebDriver driver = new FirefoxDriver(options);
        driver.get(url);
        driver.manage().window().maximize();

        Utils.delay(3);

        return driver;
    }

    private static WebDriver edgeDriver(String url) {
        TestLogger.logInfo("Redirect to URL: " + url);

        WebDriverManager.edgedriver().setup();
        WebDriver driver = new EdgeDriver();
        driver.get(url);
        driver.manage().window().maximize();

        Utils.delay(3);

        return driver;
    }

    private static WebDriver edgeDriverHeadless(String url) {
        TestLogger.logInfo("Redirect to URL: " + url);

        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        WebDriver driver = new EdgeDriver(options);
        driver.get(url);
        driver.manage().window().maximize();

        Utils.delay(3);

        return driver;
    }

    private static WebDriver safariDriver(String url) {
        TestLogger.logInfo("Redirect to URL: " + url);

        WebDriver driver = new SafariDriver();
        driver.get(url);
        driver.manage().window().maximize();

        Utils.delay(3);

        return driver;
    }

    static void closeDriver() {
        if (driver != null) {
            TestLogger.logInfo("Driver will be closed");
            driver.quit();
            driver = null;
        } else {
            TestLogger.logInfo("Driver is NULL");
        }
    }

    static void clickElement(By locator) {
        if (driver != null) {
            WebElement element = driver.findElement(locator);
            element.click();
        } else {
            throw new IllegalStateException("WebDriver is not initialized. Call getDriver() first.");
        }
    }

    static void sendText(By locator, String text) {
        if (driver != null) {
            WebElement element = driver.findElement(locator);
            element.clear();
            element.sendKeys(text);
            TestLogger.logInfo("Send text: " + text);
        } else {
            throw new IllegalStateException("WebDriver is not initialized. Call getDriver() first.");
        }
    }

    // Wait for element to be PRESENT in the DOM
    static WebElement waitForElementPresent(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    // Wait for element to be VISIBLE on the screen
    static WebElement waitForElementVisible(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Wait for element to be CLICKABLE
    static WebElement waitForElementClickable(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // ✅ Verify if element is PRESENT in the DOM
    public static boolean verifyElementPresent(By locator, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // ✅ Verify if element is VISIBLE
    public static boolean verifyElementVisible(By locator, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // ✅ Verify if element is CLICKABLE
    public static boolean verifyElementClickable(By locator, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}