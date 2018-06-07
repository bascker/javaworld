package com.bascker.restlet.mail.server;

import org.restlet.Context;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.Status;
import org.restlet.routing.Filter;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 基于 IP 地址的过滤器
 *
 * @author bascker
 */
public class Blocker extends Filter {

    /**
     * 被阻塞的 IP 列表
     */
    private final Set<String> blockedAddrs;

    public Blocker(final Context context) {
        super(context);
        this.blockedAddrs = new CopyOnWriteArraySet<>();
    }

    /**
     * 预处理
     * @param request
     * @param response
     * @return
     */
    @Override
    protected int beforeHandle(final Request request, final Response response) {
        int rs = STOP;
        /*
         * 不能用 request.getClientInfo().getAddress(), 它返回的是 IPv6 的地址
         * 使用 request.getResourceRef().getHostDomain()，获取 http://domain:port/path 中的 domain
         */
        if (blockedAddrs.contains(request.getResourceRef().getHostDomain())) {
            // Status.CLIENT_ERROR_FORBIDDEN 代表 403 返回码
            response.setStatus(Status.CLIENT_ERROR_FORBIDDEN, "非法 IP");
        } else {
            rs = CONTINUE;
        }

        return rs;
    }

    public Set<String> getBlockedAddrs() {
        return blockedAddrs;
    }
}
