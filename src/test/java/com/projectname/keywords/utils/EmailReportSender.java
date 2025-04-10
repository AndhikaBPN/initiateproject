package com.projectname.keywords.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;

public class EmailReportSender {

    static String folderPath = System.getProperty("user.dir") + "/allure-report/";
    static String senderUsername = ai.lulladream.keywords.utils.ConfigReader.getProperty("senderUsername");
    static String senderPassword = ai.lulladream.keywords.utils.ConfigReader.getProperty("senderPassword");
    static String receiverUsername = ai.lulladream.keywords.utils.ConfigReader.getProperty("receiverUsername");
    static String projectNameSubject = ai.lulladream.keywords.utils.ConfigReader.getProperty("projectName").toUpperCase();


    public static void sendReport(String projectName, String dateTime) {
        // Get current date-time
        LocalDateTime now = LocalDateTime.now();

        // Define format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        // Format date-time
        String formattedDateTime = now.format(formatter);

        // SMTP server details
        String host = "smtp.gmail.com";
        final String username = senderUsername;
        final String password = senderPassword;

        // Email details
        String toEmail = receiverUsername; // Change this
        String subject = "Automation Report - " + projectNameSubject + " - " + formattedDateTime;
        String body = "Dear All,\n\nPlease find the latest automation report attached. Let me know if you have any questions.\n\nBest regards,\nQA/QC Team";

        // Path to the test report
        String reportPath = folderPath + projectName +"_" + dateTime + ".html"; // Change to your report path

        // SMTP properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587"); // Gmail uses 587

        // Create session
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });


        try {

            // Create a new email message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);

            // Create a multipart message
            Multipart multipart = new MimeMultipart();

            // Add the email body
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText(body);
            multipart.addBodyPart(textPart);

            // Add the attachment
            MimeBodyPart attachmentPart = new MimeBodyPart();
            File reportFile = new File(reportPath);
            attachmentPart.attachFile(reportFile);
            multipart.addBodyPart(attachmentPart);

            // Set the content of the message
            message.setContent(multipart);

            // Send the email
            Transport.send(message);
            System.out.println("Report sent successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to send email.");
        }
    }

}
