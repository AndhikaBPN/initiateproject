package com.projectname.keywords.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class TestLogger {

    private static final Logger logger = Logger.getLogger(TestLogger.class.getName());

    public static void logInfo(String message) {
        // Setting up the logger to print to console
        Logger logger = Logger.getLogger("Logger");
        logger.setLevel(Level.ALL);
        logger.log(Level.INFO, log(message));
    }

    public static void logWarning(String message) {
        // Setting up the logger to print to console
        Logger logger = Logger.getLogger("Logger");
        logger.setLevel(Level.ALL);
        logger.log(Level.WARNING, warning(message));
    }

    private static String log(String message) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = LocalDateTime.now().format(formatter);
        return timestamp + " : " + message;
    }

    private static String warning(String message) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = LocalDateTime.now().format(formatter);
        return timestamp + " : " + message;
    }

}
