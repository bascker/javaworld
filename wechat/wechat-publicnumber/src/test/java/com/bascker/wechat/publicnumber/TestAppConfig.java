package com.bascker.wechat.publicnumber;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * AppConfig Unit Test
 *
 * @author bascker
 */
public class TestAppConfig {

    private static final Logger LOG = LoggerFactory.getLogger(TestAppConfig.class);

    @Test
    public void test () {
        final AppConfig appConfig = AppConfig.getInstance();
        LOG.info(appConfig.getToken());
    }

}
