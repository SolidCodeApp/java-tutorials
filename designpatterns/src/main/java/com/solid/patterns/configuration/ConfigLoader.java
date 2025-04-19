package com.solid.patterns.configuration;

import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {

    public Properties loadConfig() {
        Properties properties = new Properties();

        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {

            if (input == null) {
                System.out.println("Désolé, impossible de trouver le fichier config.properties.");
                return null;
            }
            properties.load(input);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties;
    }

}
