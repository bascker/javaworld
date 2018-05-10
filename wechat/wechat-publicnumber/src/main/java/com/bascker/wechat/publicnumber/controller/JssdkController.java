package com.bascker.wechat.publicnumber.controller;

import com.bascker.wechat.publicnumber.util.AppConfig;
import com.bascker.wechat.util.JssdkUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信公众号案例(三): JS-SDK 案例
 *
 * 1.JS-SDK 接入步骤
 *  1.1 微信公众号平台
 *      1) JS接口安全域名 -> 修改
 *      2) 填入域名: 同样不需要 http 这种前缀
 *
 *  1.2 Web Page
 *      1) 引入 jweixin.js 资源文件
 *      2) 完成 wx.config(), wx.ready(), wx.error() 的 JS 代码编写
 *
 *  1.3 后台
 *      1) 获取 access_token
 *      2) 获取 jsapi_ticket
 *      3) 设置 url: 即当前调用 js-sdk 的地址
 *      4) 进行 js 签名
 *      5) 将 appid, nonceStr 等放入 request 中，传递给 Web Page
 *
 * 2.JS-SDK 测试: 手机端打开
 *
 * @author bascker
 */
@RequestMapping(value = "/jssdk")
@Controller
public class JssdkController {

    private static final Logger LOG = LoggerFactory.getLogger(JssdkController.class);
    private static final String API_PREFIX = "/jssdk";
    private static final String PAGE_ROOT_PATH = "/jssdk";
    private static final AppConfig CONF = AppConfig.getInstance();

    /**
     * 微信扫一扫
     * 测试方法：微信端打开 url, 点击按钮 scanQRCode(直接返回结果)
     * @return
     */
    @RequestMapping("/qrcode")
    public String qrcode (final Model model) {
        LOG.info("WeChat QrCode start");
        final String url = CONF.getBaseUrl() + API_PREFIX + "/qrcode";
        model.addAllAttributes(before(url));
        LOG.info("WeChat QrCode end");

        return PAGE_ROOT_PATH + "/qrcode";
    }

    /**
     * 每次调用 js-sdk 都需要做的操作
     * 1.获取 access_token, jsapi_ticket...
     * 2.进行 js 签名
     * 3.传入 appid
     * @param url
     * @return
     */
    private Map<String, String> before (final String url) {
        LOG.info("do something before js-sdk start");

        final Map<String, String> data = new HashMap<>();
        data.put("appId", CONF.getAppId());

        // 1.获取 jsapi_ticket
        final String jsapiTicket = JssdkUtils.getJsapiTicket(CONF.getAppId(), CONF.getAppSecret());
        LOG.info("get jsapi_ticket {}", jsapiTicket);

        // 2.进行签名，并将签名后的数据，加入 model，提供给 wx.config()
        final Map<String, String> signMap = JssdkUtils.sign(jsapiTicket, url);
        LOG.info("get sign data {}", signMap);
        for (Map.Entry<String, String> entry : signMap.entrySet()) {
            data.put(entry.getKey(), entry.getValue());
        }

        LOG.info("do something before js-sdk end");

        return data;
    }

}
