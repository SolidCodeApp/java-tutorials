package com.solid.configuration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileConfigurationSource implements IConfigurationSource {

    private static final Logger logger = LoggerFactory.getLogger(FileConfigurationSource.class);
    private String path;

    public FileConfigurationSource(String path) {
        this.path = path;

    }

    @Override
    public Properties load() throws IOException {
        logger.info("Loading file configuration...");

        try (InputStream input = getClass().getClassLoader().getResourceAsStream(this.path)) {

            if (input == null) {
                logger.error("hibernate.config.properties file not found in the classpath");
                throw new FileNotFoundException("Missing Hibernate configuration file");
            }

            // Load properties from the file
            Properties properties = new Properties();
            properties.load(input);
            return properties;

        } catch (IOException exception) {
            logger.error("Failed to lead properties : {}", exception.getMessage(), exception);
            throw exception;

        }
    }
}