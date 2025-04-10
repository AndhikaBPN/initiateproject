package com.projectname.testlistener;

import com.projectname.keywords.utils.ConfigReader;
import com.projectname.keywords.utils.EmailReportSender;
import com.projectname.keywords.utils.TestLogger;
import com.projectname.keywords.utils.Utils;
import org.testng.ITestContext;
import org.testng.ITestListener;

public class Listener implements ITestListener {

    boolean sendEmail = ConfigReader.getBooleanProperty("sendEmail");
    String projectName = ConfigReader.getProperty("projectName");
    String dateTime = Utils.generateDateTime();

    @Override
    public void onFinish(ITestContext context) {
        String project = projectName.toLowerCase().replace(" ", "-");
        TestLogger.logInfo("Send Email: " + sendEmail);

        if(!sendEmail) {
            System.out.println("All tests finished. Email not Sent");
            return;
        }

        System.out.println("All tests finished. Sending test report...");

        // Generate test report
        Utils.generateReport(project, dateTime);

        // Send email with report
        EmailReportSender.sendReport(project, dateTime);
    }

}
