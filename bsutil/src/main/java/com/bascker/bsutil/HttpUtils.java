package com.bascker.bsutil;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Http 工具类
 *
 * @author bascker
 */
public class HttpUtils {

    private static final Logger LOG = LoggerFactory.getLogger(HttpUtils.class);
    private static final int MB = 1024;

    /**
     * 请求 url，获取其返回的 JSON 数据
     * @param url
     * @return
     * @throws IOException
     */
    public static String getReturnJson (final String url) throws IOException {
        final StringBuffer sb = new StringBuffer();
        final URL reqUrl = new URL(url);
        final HttpURLConnection conn = (HttpURLConnection) reqUrl.openConnection();
        try (final InputStreamReader reader = new InputStreamReader(conn.getInputStream())){
            final char[] buff = new char[MB];
            int len = -1;
            while ((len = reader.read(buff)) != -1) {
                sb.append(new String(buff, 0, len));
            }
        } finally {
            if (Objects.nonNull(conn)) {
                conn.disconnect();
            }
        }

        return sb.toString();
    }

    /**
     * 发送 GET 请求
     * @param url
     * @return
     */
    public static String get (final String url) {
        final HttpGet httpGet = new HttpGet(url);
        return sendRequest(httpGet);
    }

    /**
     * 发送 POST 请求
     * @param url
     * @return
     */
    public static String post (final String url) {
        final HttpPost httpPost = new HttpPost(url);
        return sendRequest(httpPost);
    }

    /**
     * 发送 POST 请求
     * @param url       请求地址
     * @param params    POST 参数
     * @return
     */
    public static String post (final String url, final Map<String, String> params) throws UnsupportedEncodingException {
        final HttpPost httpPost = new HttpPost(url);
        final List<NameValuePair> formParams = new ArrayList<>();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            // 给参数赋值
            formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        final UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(formParams, "UTF-8");
        httpPost.setEntity(urlEncodedFormEntity);

        return sendRequest(httpPost);
    }

    /**
     * 发送 Http 请求，获取返回值
     * @param request
     * @return
     */
    private static String sendRequest (final HttpRequestBase request) {
        String rs = "";

        // 1.获取 HttpClient 对象
        final CloseableHttpClient httpClient = HttpClients.createDefault();
        // 2.执行请求
        try (CloseableHttpResponse resp = httpClient.execute(request)) {
            final HttpEntity entity = resp.getEntity();
            if (Objects.nonNull(entity)) {
                rs = EntityUtils.toString(entity);
            }
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }

        return rs;
    }

    private HttpUtils () {}

}