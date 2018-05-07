package com.bascker.wechat.util;

import com.bascker.bsutil.HttpUtils;
import com.bascker.bsutil.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * JS-SDK 工具类
 *
 * @author bascker
 */
public class JssdkUtils {

    private static final Logger LOG = LoggerFactory.getLogger(JssdkUtils.class);
    private static final Map<String, Object> CACHE = new ConcurrentHashMap<>();

    public static Map<String, String> sign (final String jsapiTicket) {
        return sign(jsapiTicket, "");
    }

    /**
     * 签名算法实现
     *
     * @param jsapiTicket   公众号用于调用微信JS接口的临时票据, 有效期为7200秒，通过access_token来获取
     *                      由于获取jsapi_ticket的api调用次数非常有限，频繁刷新jsapi_ticket会导致api
     *                      调用受限，影响自身业务，开发者必须在自己的服务全局缓存jsapi_ticket
     * @param url           当前网页的 URL
     * @return map          包含 wx.config() 需要的属性
     */
    public static Map<String, String> sign (final String jsapiTicket, final String url) {
        final Map<String, String> map = new HashMap<>();
        final String nonceStr = createNonceStr();
        final String timestamp = createTimestamp();
        String string1;
        String signature = "";

        // 注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapiTicket +
                "&noncestr=" + nonceStr +
                "&timestamp=" + timestamp +
                "&url=" + url;
        LOG.info(string1);

        try {
            final MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        map.put("url", url);
        map.put("jsapi_ticket", jsapiTicket);
        map.put("nonceStr", nonceStr);
        map.put("timestamp", timestamp);
        map.put("signature", signature);

        return map;
    }

    /**
     * 获取 access_token
     * @param appId
     * @param appSecret
     * @return
     */
    public static String getAccessToken (final String appId, final String appSecret) {
        final AccessToken at = (AccessToken) CACHE.get(AccessToken.KEY);
        if (Objects.nonNull(at) && at.isValidate()) {
            return at.getTicket();
        }

        final String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential"
                + "&appid=" + appId
                + "&secret=" + appSecret;
        final String retJson = HttpUtils.get(url);
        String accessToken = "";
        try {
            final Map<String, Object> map = JsonUtils.toMap(retJson);
            accessToken = (String) map.getOrDefault("access_token", "");
            CACHE.put(AccessToken.KEY, new AccessToken(accessToken));
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }

        return accessToken;
    }

    /**
     * 获取 jsapi_ticket:
     *  1.公众号用于调用微信JS接口的临时票据, 有效期为7200秒，通过access_token来获取
     *  2.由于获取jsapi_ticket的api调用次数非常有限，频繁刷新jsapi_ticket会导致api调用受限，影响自身业务，开发者必须在自己的服务全局缓存jsapi_ticket
     * @param appId
     * @param appSecret
     * @return
     */
    public static String getJsapiTicket (final String appId, final String appSecret) {
        final JsapiTicket jsapiTicket = (JsapiTicket) CACHE.getOrDefault(JsapiTicket.KEY, null);
        if (Objects.nonNull(jsapiTicket) && jsapiTicket.isValidate()) {
            return jsapiTicket.getTicket();
        }

        return getJsapiTicket(getAccessToken(appId, appSecret));
    }

    /**
     * 获取 jsapi_ticket
     *
     * @return
     */
    public static String getJsapiTicket (final String accessToken) {
        final String url = "http://api.weixin.qq.com/cgi-bin/ticket/getticket?type=jsapi"
                + "&access_token=" + accessToken;
        final String retJson = HttpUtils.get(url);
        String jsapiTicket = "";
        try {
            final Map<String, Object> map = JsonUtils.toMap(retJson);
            jsapiTicket = (String) map.getOrDefault("ticket", "");

            CACHE.put(JsapiTicket.KEY, new JsapiTicket(jsapiTicket));
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }

        return jsapiTicket;
    }

    private static String byteToHex(final byte[] hash) {
        String rs = "";
        try (final Formatter formatter = new Formatter()) {
            for (byte b : hash) {
                formatter.format("%02x", b);
            }
            rs = formatter.toString();
        }

        return rs;
    }

    private static String createNonceStr() {
        return UUID.randomUUID().toString();
    }

    private static String createTimestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }

    private JssdkUtils() {}

}