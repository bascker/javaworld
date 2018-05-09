package com.bascker.wechat.publicnumber.model;

import com.bascker.bsutil.JsonUtils;

import java.io.IOException;
import java.util.Map;

/**
 * 请求网页授权 access_token 的返回值
 *
 * @author bascker
 */
public class AccessTokenRetValue {

    private String mAccessToken;        // 网页授权接口调用凭证, 此 access_token 与基础支持的 access_token 不同
    private int mExpiresIn;             // access_token 接口调用凭证超时时间，单位（秒）
    private String mRefreshToken;       // 用户刷新 access_token
    private String mOpenid;             // 用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的 OpenID
    private String mScope;              // 用户授权的作用域，使用逗号（,）分隔

    public AccessTokenRetValue() {}

    /**
     * 根据 json 串创建对象
     * @param jsonStr
     * @return
     */
    public static AccessTokenRetValue createFromJson (final String jsonStr) throws IOException {
        final Map<String, Object> map = JsonUtils.toMap(jsonStr);
        final AccessTokenRetValue obj = new AccessTokenRetValue();
        obj.setAccessToken((String) map.get("access_token"));
        obj.setExpiresIn((int) map.get("expires_in"));
        obj.setRefreshToken((String) map.get("refresh_token"));
        obj.setOpenid((String) map.get("openid"));
        obj.setScope((String) map.get("scope"));

        return obj;
    }

    // --------------------------
    // Getter/Setter
    // --------------------------

    public String getAccessToken() {
        return mAccessToken;
    }

    public void setAccessToken(final String accessToken) {
        mAccessToken = accessToken;
    }

    public int getExpiresIn() {
        return mExpiresIn;
    }

    public void setExpiresIn(final int expiresIn) {
        mExpiresIn = expiresIn;
    }

    public String getRefreshToken() {
        return mRefreshToken;
    }

    public void setRefreshToken(final String refreshToken) {
        mRefreshToken = refreshToken;
    }

    public String getOpenid() {
        return mOpenid;
    }

    public void setOpenid(final String openid) {
        mOpenid = openid;
    }

    public String getScope() {
        return mScope;
    }

    public void setScope(final String scope) {
        mScope = scope;
    }

    @Override
    public String toString() {
        return "AccessTokenRetValue{" +
                "mAccessToken='" + mAccessToken + '\'' +
                ", mExpiresIn=" + mExpiresIn +
                ", mRefreshToken='" + mRefreshToken + '\'' +
                ", mOpenid='" + mOpenid + '\'' +
                ", mScope='" + mScope + '\'' +
                '}';
    }
}
