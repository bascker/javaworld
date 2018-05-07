package com.bascker.wechat.publicnumber;

import com.bascker.bsutil.FileUtils;
import com.bascker.bsutil.PropertiesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Objects;
import java.util.Properties;

/**
 * WeChat Config
 *
 * @author bascker
 */
public class AppConfig {

    private static final Logger LOG = LoggerFactory.getLogger(AppConfig.class);
    private static final String CONFIG_FILENAME = "wechat.properties";
    private static volatile AppConfig instance = null;

    private String appId;
    private String appSecret;
    private String baseUrl;
    private String token;

    public static AppConfig getInstance () {
        if (Objects.isNull(instance)) {
            synchronized (AppConfig.class) {
                if (Objects.isNull(instance)) {
                    instance = new AppConfig();
                }
            }
        }

        return instance;
    }

    private AppConfig () {
        init();
    }

    private void init () {
        final String path = FileUtils.getFileInResources(CONFIG_FILENAME).getFile();
        final Properties properties = PropertiesUtils.load(new File(path));
        appId = properties.getProperty("appId");
        appSecret = properties.getProperty("appSecret");
        baseUrl = properties.getProperty("baseUrl");
        token = properties.getProperty("token");
    }

    public String getAppId() {
        return appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getToken() {
        return token;
    }
}
