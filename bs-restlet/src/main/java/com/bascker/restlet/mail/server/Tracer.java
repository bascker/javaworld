package com.bascker.restlet.mail.server;

import org.restlet.Context;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.Restlet;
import org.restlet.data.MediaType;

/**
 * 对资源进行响应
 *
 * @author bascker
 */
public class Tracer extends Restlet {

    public Tracer() {}

    public Tracer(final Context context) {
        super(context);
    }

    /**
     * 处理请求
     *
     * @param request
     * @param response
     */
    @Override
    public void handle(final Request request, final Response response) {
        final StringBuilder sb = new StringBuilder();
        sb.append("方法: ").append(request.getMethod())
            .append("\n资源 URI：").append(request.getResourceRef())
            .append("\nIP：").append(request.getClientInfo().getAddress())
            .append("\n代理名称：").append(request.getClientInfo().getAgentName())
            .append("\n代理版本：").append(request.getClientInfo().getAgentVersion());

        response.setEntity(sb.toString().trim(), MediaType.TEXT_PLAIN);
    }
}
