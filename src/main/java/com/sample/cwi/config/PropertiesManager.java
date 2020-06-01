package com.sample.cwi.config;

import java.util.Properties;

public class PropertiesManager {

    private static Properties properties;

    public PropertiesManager(Properties prop) {
        properties = prop;
    }

    public int getIntProperty(String key) {
        return Integer.parseInt(properties.getProperty(key));
    }

    public String getStringProperty(String key){
        return properties.getProperty(key);
    }

}
