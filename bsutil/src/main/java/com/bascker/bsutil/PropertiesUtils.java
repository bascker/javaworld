package com.bascker.bsutil;

import org.apache.commons.io.Charsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Properties Utils
 *
 * @author bascker
 */
public class PropertiesUtils {

    private static final Logger LOG = LoggerFactory.getLogger(PropertiesUtils.class);
    private static final Map<String, Properties> CACHE = new HashMap<>();

    public static Properties load (final File file){
        final String filename = file.getName();
        if (CACHE.containsKey(filename)) {
            return CACHE.get(filename);
        }

        Properties properties = null;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), Charsets.UTF_8))) {
            properties = new Properties();
            properties.load(reader);
            CACHE.put(filename, properties);
        } catch (IOException e) {
            LOG.error("PropertiesUtils.load: " + e.getCause().toString());
        }

        return properties;
    }

    private PropertiesUtils () {}

}
