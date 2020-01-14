package com.loyalty.pwa.appium.utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {
    private Properties properties;
    private final String propertyFilePath= "src/test/resources/configuration.properties";


    public ConfigFileReader() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
        }
    }

    public String getAppiumUrl() {
        String appiumUrl = properties.getProperty("appium.url");
        if(appiumUrl != null) { return appiumUrl; }
        else throw new RuntimeException("appiumUrl not specified in the Configuration.properties file.");
    }

    public String getAppUrl() {
        String appUrl = properties.getProperty("app.url");
        if(appUrl != null) { return appUrl; }
        else throw new RuntimeException("appUrl not specified in the Configuration.properties file.");
    }
}