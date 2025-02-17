package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    public static String readProperty(String key) {

        //File class assign given file to the Java object
        File file = new File("configuration.properties");
        //Properties object will read the properties file
        Properties properties = new Properties();
        try {
            properties.load((new FileInputStream(file)));
            //load method will load every value from file object to the
            //properties object.
        } catch (IOException e) {
            e.printStackTrace();
        }
        //more actions surround by try and catch.
        return properties.getProperty(key);
        //I can read the value from properties object using the key.
    }

}

