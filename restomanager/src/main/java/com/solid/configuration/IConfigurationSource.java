package com.solid.configuration;

import java.io.IOException;
import java.util.Properties;

public interface IConfigurationSource {

    Properties load() throws IOException;

}
