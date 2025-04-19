package com.solid.patterns.configuration;

import java.util.Properties;

public class DatabaseConfig {

    private Properties properties;

    public DatabaseConfig() {
        ConfigLoader configLoader = new ConfigLoader();
        this.properties = configLoader.loadConfig();
    }

    public String getDbUrl() {
        return this.properties.getProperty("db.url");
    }

    public String getUsername() {
        return this.properties.getProperty("db.username");
    }

    public String getPassword() {
        return this.properties.getProperty("db.paswword");
    }

}
