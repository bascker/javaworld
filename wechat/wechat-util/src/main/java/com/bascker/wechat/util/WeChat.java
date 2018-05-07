package com.bascker.wechat.util;

/**
 * WeChat
 *
 * @author bascker
 */
public class WeChat {

    // ----------------------------
    // scope: 应用授权作用域
    // ----------------------------

    /**
     * 以 snsapi_base 为 scope 发起的网页授权
     * 1.用来获取进入页面的用户的 openid: 不弹出授权页面，直接跳转，只能获取用户 openid
     * 2.静默授权并自动跳转到回调页的：用户感知的就是直接进入了回调页（往往是业务页面）
     */
    public static final String SCOPE_BASE = "snsapi_base";

    /**
     * 以 snsapi_userinfo 为 scope 发起的网页授权
     * 1.用来获取用户的基本信息: 弹出授权页面，可通过 openid 拿到昵称、性别、所在地
     * 2.这种授权需要用户手动同意，并且由于用户同意过，所以无须关注，就可在授权后获取该用户的基本信息
     */
    public static final String SCOPE_INFO = "snsapi_userinfo";

}
