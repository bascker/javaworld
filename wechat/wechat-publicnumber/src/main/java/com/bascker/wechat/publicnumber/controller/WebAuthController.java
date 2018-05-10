package com.bascker.wechat.publicnumber.controller;

import com.bascker.bsutil.HttpUtils;
import com.bascker.wechat.publicnumber.model.AccessTokenRetValue;
import com.bascker.wechat.publicnumber.model.User;
import com.bascker.wechat.publicnumber.util.AppConfig;
import com.bascker.wechat.util.WeChat;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Objects;

/**
 * 微信公众号案例(二): 网页授权案例
 *
 * 1.网页授权接入步骤
 *  1) 引导用户进入授权页面同意授权，获取 code
 *  2) 通过 code 换取网页授权 access_token: 与基础支持中的 access_token 不同
 *  3) 如果需要，开发者可以刷新网页授权 access_token，避免过期
 *  4) 通过网页授权 access_token 和 openid 获取用户基本信息(支持 UnionID 机制)
 *
 * 2.WeChat 配置:
 *  1) 查看接口权限表, 若是测试号，就是"体验接口权限表"
 *  2) 网页服务 -> 网页账号 -> 网页授权获取用户基本信息 -> 修改
 *  3) 填入授权回调页面域名: 对应 wechat.properties 中的 baseUrl，只能填域名(如 vnupzm.natappfree.cc)，否则报错
 *
 * 3.网页授权测试: 手机微信打开地址, 如 http://vnupzm.natappfree.cc/webauth, 能看到 webAuth.jsp 的内容则表示成功
 *
 * @author bascker
 */
@RequestMapping("/webauth")
@Controller
public class WebAuthController {

    private static final Logger LOG = LoggerFactory.getLogger(WebAuthController.class);
    private static final String REDIRECT = "redirect:";
    private static final AppConfig CONF = AppConfig.getInstance();

    /**
     * Web 授权
     */
    @RequestMapping
    public String auth () throws UnsupportedEncodingException {
        LOG.info("auth start");

        // 1.引导用户进入授权页面同意授权，获取code
        String redirectUri = CONF.getBaseUrl() + "/webauth/user";          // 回调页(业务页面)地址
        redirectUri = URLEncoder.encode(redirectUri, "UTF-8");           // 需使用 urlEncode 对链接进行处理

        final String state = "ok";        // 非必须属性，重定向后会带上state参数，开发者可以填写a-zA-Z0-9的参数值，最多128字节
        final String reqUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?"
                + "appid=" + CONF.getAppId()
                + "&redirect_uri=" + redirectUri
                + "&response_type=code"
                + "&scope=" + WeChat.SCOPE_INFO
                + "&state=" + state
                + "#wechat_redirect";     // 无论直接打开还是做页面302重定向时候，必须带此参数

        LOG.info("auth end");

        // 若用户同意授权，页面将跳转至 redirect_uri/?code=CODE&state=STATE 页面
        return REDIRECT + reqUrl;
    }

    /**
     * 回调界面：获取用户信息，并展示
     * @param code 换取 access_token 的票据，每次用户授权带上的 code 将不一样，code 只能使用一次，5min 未被使用自动过期
     * @return
     */
    @RequestMapping("/user")
    public String userinfo (@RequestParam(name = "code") final String code,
                            final Model model) throws IOException {
        LOG.info("get userinfo start");
        if (Objects.nonNull(code) && StringUtils.isNotEmpty(code)) {
            // 2.获取 access_token
            String retJson = getAccessToken(code);
            if (retJson.contains("errcode")) {
                model.addAttribute("msg", retJson);
                return "/msg";
            }
            AccessTokenRetValue atrv = AccessTokenRetValue.createFromJson(retJson);

            // 3.刷新 access_token
            retJson = refreshAccessToken(atrv.getRefreshToken());
            if (retJson.contains("errcode")) {
                model.addAttribute("msg", retJson);
                return "/msg";
            }
            atrv = AccessTokenRetValue.createFromJson(retJson);

            // 4.通过 access_token 和 openid 拉取用户信息
            retJson = pullUserInfo(atrv.getAccessToken(), atrv.getOpenid());
            final User user = User.createFromJson(retJson);
            model.addAttribute("user", user);
        } else {
            model.addAttribute("msg", "用户禁止授权！");
            return "/msg";
        }
        LOG.info("get userinfo end");

        return "/webAuth";
    }

    /**
     * 通过 code 换取网页授权 access_token（与基础支持中的access_token不同）
     * @param code  换取 access_token 的票据，每次用户授权带上的 code 将不一样，code 只能使用一次，5min 未被使用自动过期
     * @return
     * @throws IOException
     */
    private String getAccessToken (final String code) throws IOException {
        LOG.info("get access_token start");
        final String accessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?"
                + "appid=" + CONF.getAppId()
                + "&secret=" + CONF.getAppSecret()
                + "&code=" + code
                + "&grant_type=authorization_code";
        final String retJson = HttpUtils.getReturnJson(accessTokenUrl);
        LOG.info("get access_token end");

        return retJson;
    }

    /**
     * 刷新 access_token（如果需要）,避免过期
     * @param refreshToken
     * @return
     * @throws IOException
     */
    private String refreshAccessToken (final String refreshToken) throws IOException {
        LOG.info("refresh access_token start");
        final String refreshTokenUrl = "https://api.weixin.qq.com/sns/oauth2/refresh_token?"
                + "appid=" + CONF.getAppId()
                + "&grant_type=refresh_token"
                + "&refresh_token=" + refreshToken;
        final String retJson = HttpUtils.getReturnJson(refreshTokenUrl);
        LOG.info("refresh access_token end");

        return retJson;
    }

    /**
     * 通过 access_token 和 openid 拉取用户信息：要求 scope 为 snsapi_userinfo
     * @return
     */
    private String pullUserInfo (final String accessToken, final String openid) throws IOException {
        LOG.info("pull user info start");
        final String pullUserinfoUrl = "https://api.weixin.qq.com/sns/userinfo?"
                + "access_token=" + accessToken
                + "&openid=" + openid
                + "&lang=zh_CN";            // 返回国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语
        final String retJson = HttpUtils.getReturnJson(pullUserinfoUrl);
        LOG.info("pull user info end");

        return retJson;
    }
}
