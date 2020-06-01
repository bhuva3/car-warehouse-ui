package com.sample.cwi.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertyFileReader {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyFileReader.class);

    public static Properties readPropertyFile(String... args) throws IOException {
        InputStream inputStream = null;
        Properties properties = new Properties();

        try {
            inputStream = new FileInputStream(Paths.get(args[1]).toFile());
            properties.load(inputStream);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Mentioned property file " + args[1] + " was not present !");
        } catch (IOException e) {
            throw new IOException("Error reading property file " + args[1]);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    LOGGER.error("Error occurred while closing inputStream [{}]", e);
                }
            }
        }

        return properties;
    }

}
