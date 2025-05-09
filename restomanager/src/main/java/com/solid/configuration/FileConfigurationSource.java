package com.solid.configuration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of IConfigurationSource that loads configuration properties
 * from a file located in the application's classpath.
 */
public class FileConfigurationSource implements IConfigurationSource {

    private static final Logger logger = LoggerFactory.getLogger(FileConfigurationSource.class);
    private String path;

    /**
     * Constructs a FileConfigurationSource with the specified path to the
     * properties file.
     *
     * @param path the relative path to the configuration file in the classpath
     */
    public FileConfigurationSource(String path) {
        this.path = path;
    }

    /**
     * Loads the configuration properties from the file.
     *
     * @return a Properties object containing key-value pairs from the file
     * @throws IOException if the file is not found or cannot be read
     */
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
            logger.error("Failed to load properties: {}", exception.getMessage(), exception);
            throw exception;
        }
    }
}
