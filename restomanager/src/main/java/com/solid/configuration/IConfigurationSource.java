package com.solid.configuration;

import java.io.IOException;
import java.util.Properties;

/**
 * Represents a source of configuration properties.
 * Implementations of this interface must define how the properties are loaded.
 */
public interface IConfigurationSource {

    /**
     * Loads configuration properties.
     *
     * @return a Properties object containing configuration key-value pairs
     * @throws IOException if an error occurs during loading
     */
    Properties load() throws IOException;

}
