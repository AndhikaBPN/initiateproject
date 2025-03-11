package com.projectname.keywords.driver;

import com.projectname.keywords.utils.TestLogger;
import com.projectname.keywords.utils.Utils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverManager {

    static ChromeDriver chrome;
    static FirefoxDriver firefox;
    static EdgeDriver edge;
    static SafariDriver safari;
    private static WebDriver driver;

    public static void getDriver(String driver, String url) {
        switch (driver.toLowerCase()) {
            case "chrome":
                chromeDriver(url);
                break;
            case "firefox":
                firefoxDriver(url);
                break;
            case "edge":
                edgeDriver(url);
                break;
            case "safari":
                safariDriver(url);
                break;
            default:
                chromeDriver(url);
                break;
        }
    }

    private static void chromeDriver(String url) {
        TestLogger.logInfo("Redirect to URL: " + url);

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();

        Utils.delay(5);
    }

    private static void firefoxDriver(String url) {
        TestLogger.logInfo("Redirect to URL: " + url);

        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get(url);
        driver.manage().window().maximize();

        Utils.delay(5);
    }

    private static void edgeDriver(String url) {
        TestLogger.logInfo("Redirect to URL: " + url);

        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.get(url);
        driver.manage().window().maximize();

        Utils.delay(5);
    }

    private static void safariDriver(String url) {
        TestLogger.logInfo("Redirect to URL: " + url);

        driver = new SafariDriver();
        driver.get(url);
        driver.manage().window().maximize();

        Utils.delay(5);
    }

    public static void closeDriver() {
        if (driver != null) {
            TestLogger.logInfo("Driver will be closed");
            driver.quit();
            driver = null;
        } else {
            TestLogger.logInfo("Driver is NULL");
        }
    }

}
