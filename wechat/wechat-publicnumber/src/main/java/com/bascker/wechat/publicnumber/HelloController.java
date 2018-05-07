package com.bascker.wechat.publicnumber;

import com.bascker.wechat.util.WechatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Hello WeChat Public Number: 微信公众号接入
 *
 * 1.WeChat 公众号接入步骤
 *  1) 获取 WX 服务器传递过来的请求数据: signature, timestamp, nonce, echostr
 *  2) 校验该 GET 请求确实来自 WX 服务器
 *  3) 返回 echostr 参数内容，接入 WX
 *
 * @author bascker
 */

@RequestMapping("/hello")
@Controller
public class HelloController {

    private static final Logger LOG = LoggerFactory.getLogger(HelloController.class);

    /**
     * WX 接入
     *
     * @param signature     微信加密签名
     * @param timestamp     时间戳
     * @param nonce         随机数
     * @param echoStr       随机字符串
     * @param resp
     */
    @RequestMapping
    public void access (@RequestParam(name = "signature") final String signature,
                       @RequestParam(name = "timestamp") final String timestamp,
                       @RequestParam(name = "nonce") final String nonce,
                       @RequestParam(name = "echostr") final String echoStr,
                       final HttpServletResponse resp) {
        // 1.获取 WX 服务器传递过来的请求数据
        LOG.info("signature: " + signature);

        // 2.校验该 GET 请求确实来自微信服务器
        if (WechatUtils.checkSignature(signature, AppConfig.getInstance().getToken(), timestamp, nonce)) {
            LOG.info("请求来自 WX Server, 准备接入");

            // 3.返回 echostr 参数内容，接入 WX
            try (final PrintWriter out = resp.getWriter()) {
                LOG.info("echostr: " + echoStr);
                out.println(echoStr);
            } catch (IOException e) {
                LOG.error(e.getMessage());
            }
        }
    }

}