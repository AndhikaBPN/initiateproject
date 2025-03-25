package ai.lulladream.keywords.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static void openBrowser(String driver, String url) {
        DriverManager.getDriver(driver, url);
    }

    public static void closeBrowser() {
        DriverManager.closeDriver();
    }

    public static void clickElement(By locator) {
        waitForElementClickable(locator, 10);
        DriverManager.clickElement(locator);
    }

    public static void sendText(By locator, String text) {
        waitForElementPresent(locator, 10);
        DriverManager.sendText(locator, text);
    }

    public static void waitForElementPresent(By locator, int timeout) {
        DriverManager.waitForElementPresent(locator, timeout);
    }

    public static void waitForElementVisible(By locator, int timeout) {
        DriverManager.waitForElementVisible(locator, timeout);
    }

    public static void waitForElementClickable(By locator, int timeout) {
        DriverManager.waitForElementClickable(locator, timeout);
    }

    public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir")+"/FailedTestScreenshot/"
                +screenshotName+"_"+dateName+".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        return destination;
    }

    public static void delay(int second) {
        try {
            Thread.sleep(1000*second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void scrollByVisibleElement(WebElement element, WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("arguments[0].scrollIntoView();",element );
    }

    public static void scrollDownByPixel(int vertical, WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("window.scrollBy(0,"+vertical+")");
    }

    public static void scrollDownToButtom(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public static void zoomOut(int times){
        try {
            Robot robot = new Robot();
            for (int i = 0; i < times; i++) {
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_SUBTRACT);
                robot.keyRelease(KeyEvent.VK_CONTROL);
                robot.keyRelease(KeyEvent.VK_SUBTRACT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void zoomIn(int times){
        try {
            Robot robot = new Robot();
            for (int i = 0; i < times; i++) {
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_ADD);
                robot.keyRelease(KeyEvent.VK_CONTROL);
                robot.keyRelease(KeyEvent.VK_ADD);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
