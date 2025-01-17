package org.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class PropertiesManager {

    private static final String propFilePath = "src\\main\\resources\\SimpleBook.properties";

    public static String getProperty(String propertyName) {
        Properties properties = new Properties();
        try(FileInputStream input = new FileInputStream(propFilePath)) {
            properties.load(input);
            return properties.getProperty(propertyName);
        }  catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

