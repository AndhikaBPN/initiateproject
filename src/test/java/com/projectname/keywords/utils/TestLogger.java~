package com.projectname.keywords.utils;

import org.testng.Assert;

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
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new SimpleFormatter());
        logger.addHandler(consoleHandler);
        logger.setUseParentHandlers(false);
        log(message);
    }

    public static void logWarning(String message) {
        // Setting up the logger to print to console
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new SimpleFormatter());
        logger.addHandler(consoleHandler);
        logger.setUseParentHandlers(false);
        warning(message);
    }

    private static void log(String message) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = LocalDateTime.now().format(formatter);
        logger.info(timestamp + " : " + message);
    }

    private static void warning(String message) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = LocalDateTime.now().format(formatter);
        logger.warning(timestamp + " : " + message);
    }

}
