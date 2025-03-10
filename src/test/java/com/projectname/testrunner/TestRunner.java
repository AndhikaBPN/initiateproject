package com.projectname.testrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {
                "src/test/resources/features/open_chrome/chrome.feature"
        },
        glue = {
                "com.projectname.stepdefinition",
        },
        plugin = {
                "pretty",
                "html:target/cucumberReports/Report.html",
                "json:target/cucumberReports/Report.json",
                "junit:target/cucumberReports/Report.xml",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        }
)

// use allure generate --clean --single-file command to generate report
public class TestRunner extends AbstractTestNGCucumberTests {

}