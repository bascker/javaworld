package com.bascker.wechat.util;

/**
 * JS-API Ticket
 *
 * @author bascker
 */
public class JsapiTicket extends BaseToken {

    public static final String KEY = "jsapi_ticket";

    public JsapiTicket(final String ticket) {
        super(ticket);
    }

}
