package com.projectname.keywords.driver;

import com.projectname.keywords.Utils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.Locale;

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

    public static void chromeDriver(String url) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();

        Utils.delay(5);
    }

    public static void firefoxDriver(String url) {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get(url);
        driver.manage().window().maximize();

        Utils.delay(5);
    }

    public static void edgeDriver(String url) {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.get(url);
        driver.manage().window().maximize();

        Utils.delay(5);
    }

    public static void safariDriver(String url) {
        driver = new SafariDriver();
        driver.get(url);
        driver.manage().window().maximize();

        Utils.delay(5);
    }

    public static void closeDriver() {
        if (driver != null) {
            System.out.println("driver will be closed");
            driver.quit();
            driver = null;
        } else {
            System.out.println("driver is NULL");
        }
    }

}
