package ai.lulladream.keywords.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DriverManager {

    static ChromeDriver chrome;
    static FirefoxDriver firefox;
    static EdgeDriver edge;
    static SafariDriver safari;
    private static WebDriver driver;

    static WebDriver getDriver(String platform, String url) {
        switch (platform.toLowerCase()) {
            case "chrome":
                driver = chromeDriver(url);
                break;
            case "firefox":
                driver = firefoxDriver(url);
                break;
            case "edge":
                driver = edgeDriver(url);
                break;
            case "safari":
                driver = safariDriver(url);
                break;
            default:
                driver = chromeDriver(url);
                break;
        }

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

    private static WebDriver firefoxDriver(String url) {
        TestLogger.logInfo("Redirect to URL: " + url);

        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
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

}
