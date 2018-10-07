package com.bascker.bsutil;

import org.apache.commons.io.Charsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

/**
 * Properties Utils
 *
 * @author bascker
 */
public class PropertiesUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesUtils.class);
    private static final Map<String, Properties> CACHE = new HashMap<>();

    /**
     * 加载 properties 属性文件
     * @param file
     * @return
     */
    public static Properties load(final File file){
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
            LOGGER.error("load properties file failure", e);
        }

        return properties;
    }

    /**
     * 加载 properties 属性文件
     * @param filename
     * @return
     */
    public static Properties load(final String filename) {
        if (CACHE.containsKey(filename)) {
            return CACHE.get(filename);
        }

        Properties props = null;
        try(BufferedReader reader = new BufferedReader(
                new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream(filename)))) {
            if (Objects.isNull(reader)) {
                throw new FileNotFoundException(filename + " file is not found");
            }
            props = new Properties();
            props.load(reader);
            CACHE.put(filename, props);
        } catch (IOException e) {
            LOGGER.error("load properties file failure", e);
        }

        return props;
    }

    /**
     * 获取字符串属性，默认值为空串
     * @param props
     * @param key
     * @return
     */
    public static String getString(final Properties props, final String key) {
        return getString(props, key, Constant.EMPTY_STR);
    }

    /**
     * 获取字符串属性
     * @param props
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getString(final Properties props, final String key, String defaultValue) {
        return props.containsKey(key) ? props.getProperty(key) : defaultValue;
    }

    /**
     * 获取数值型属性，默认值为 0
     * @param props
     * @param key
     * @return
     */
    public static int getInt(final Properties props, final String key) {
        return getInt(props, key, 0);
    }

    /**
     * 获取数值型属性
     * @param props
     * @param key
     * @param defaultValue
     * @return
     */
    public static int getInt(final Properties props, final String key, final int defaultValue) {
        return props.containsKey(key) ? CastUtils.castInt(props.getProperty(key)) : defaultValue;
    }

    /**
     * 获取布尔属性值，默认为 false
     * @param props
     * @param key
     * @return
     */
    public static boolean getBoolean(final Properties props, final String key) {
        return getBoolean(props, key, Boolean.FALSE);
    }

    /**
     * 获取布尔属性值
     * @param props
     * @param key
     * @param defaultValue
     * @return
     */
    public static boolean getBoolean(final Properties props, final String key, final boolean defaultValue) {
        return props.containsKey(key) ? CastUtils.castBoolean(props.getProperty(key)) : defaultValue;
    }

    private PropertiesUtils () {}

}
