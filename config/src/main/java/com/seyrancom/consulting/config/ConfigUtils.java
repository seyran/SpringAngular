package com.seyrancom.consulting.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.util.Properties;

public class ConfigUtils {

    public static Properties loadProperties(String propertyFileName)
    {
        return loadProperties(propertyFileName, new Properties());
    }

    public static Properties loadProperties(String propertyFileName, Properties props)
    {
        final File propFile = new File(propertyFileName);
        final Class<?> cls = MethodHandles.lookup().lookupClass();
        try (final InputStream is = propFile.isFile() ? new FileInputStream(propFile) : cls.getResourceAsStream(propertyFileName)) {
            if (is != null) {
                props.load(is);
            }
            else {
                throw new IllegalArgumentException("Cannot find property file: " + propertyFileName);
            }
        }
        catch (IOException io) {
            throw new RuntimeException("Failed to read property file", io);
        }

        return props;
    }
}
