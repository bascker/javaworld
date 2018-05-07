package com.bascker.wechat.util;

/**
 * AccessToken
 *
 * @author bascker
 */
public class AccessToken extends BaseToken {

    public static final String KEY = "access_token";

    public AccessToken (final String ticket) {
        super(ticket);
    }

}
