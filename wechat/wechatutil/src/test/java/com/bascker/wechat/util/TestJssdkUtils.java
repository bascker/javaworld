package com.bascker.wechat.util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * JssdkUtils Unit Test
 *
 * @author bascker
 */
public class TestJssdkUtils {

    private static final Logger LOG = LoggerFactory.getLogger(TestJssdkUtils.class);

    private String appId = "";
    private String appSecret = "";
    private String url = "http://dqcqcg.natappfree.cc/jssdk/qrcode/scan";

    @Test
    public void testSign () {
        final String jsapiTicket = JssdkUtils.getJsapiTicket(appId, appSecret);
        final Map<String, String> map = JssdkUtils.sign(jsapiTicket, url);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            LOG.info(entry.getKey() + ":" + entry.getValue());
        }
    }

}