package com.projectname.keywords.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    static String prjPath = System.getProperty("user.dir");

    public static void openBrowser(Platform platform, String url) {
        DriverManager.getDriver(platform, url);
    }

    public static WebDriver getDriver() {
        return DriverManager.getWebDriver();
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

    public static String getText(By locator) {
        return DriverManager.getText(locator);
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

    public static boolean verifyElementPresent(By locator, int timeout) {
        return DriverManager.verifyElementPresent(locator, timeout);
    }

    public static boolean verifyElementVisible(By locator, int timeout) {
        return DriverManager.verifyElementVisible(locator, timeout);
    }

    public static boolean verifyElementClickable(By locator, int timeout) {
        return DriverManager.verifyElementClickable(locator, timeout);
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

    public static void scrollDownToBottom(WebDriver driver) {
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

    public static void generateReport(String projectName, String dateTime) {
        System.out.println("Generate Report...");

        try {
            ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", "allure generate target/allure-results --clean --single-file");
            builder.redirectErrorStream(true); // Merge error stream into output stream
            Process process = builder.start();

            // Read and print output from CMD
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Rename file
            changeFileReportName(projectName, dateTime);

            int exitCode = process.waitFor(); // Wait for command to complete
            System.out.println("Report generated successfully!");
            System.out.println("Exit Code: " + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void changeFileReportName(String projectName, String dateTime) {
        File oldFile = new File(prjPath + "/allure-report/index.html");
        File newFile = new File(prjPath + "/allure-report/" + projectName + "_" + dateTime + ".html");

        System.out.println("Rename Report File...");

        if (oldFile.renameTo(newFile)) {
            System.out.println("Allure report renamed successfully!");
        } else {
            System.out.println("Failed to rename report.");
        }
    }

    public static String generateDateTime() {
        return new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
    }

    public static void playAudio() {
        DriverManager.playAudio();
    }

    public static void verifyAudioplayed(int waitSec) {
        boolean isPlaying = DriverManager.verifyAudioPlayer(waitSec);

        if(isPlaying) {
            TestLogger.logInfo("Audio is playing.");
        } else {
            TestLogger.logWarning("Audio is not playing.");
        }
    }

}
