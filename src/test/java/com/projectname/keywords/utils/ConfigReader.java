package com.projectname.keywords.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties = new Properties();
    private static String environment; // Stores the selected environment

    static {
        try {
            // Read the environment from a system property or use "staging" as default
            environment = System.getProperty("env");

            if(environment == null || environment.isEmpty()) {
                environment = "staging";
            }

            environment.toLowerCase();

            System.out.println("ENVV: " + environment);
            String filePath = "src/test/resources/env/" + environment + ".properties";

            FileInputStream file = new FileInputStream(filePath);
            properties.load(file);
            file.close();
            System.out.println("Loaded properties from: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load properties file for environment: " + environment);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static int getIntProperty(String key) {
        return Integer.parseInt(properties.getProperty(key));
    }

    public static boolean getBooleanProperty(String key) {
        return Boolean.parseBoolean(properties.getProperty(key));
    }

    public static String getEnvironment() {
        return environment;
    }

}
