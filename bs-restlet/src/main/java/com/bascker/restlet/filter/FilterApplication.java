package com.bascker.restlet.filter;

import org.restlet.Application;
import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.Server;
import org.restlet.data.Protocol;
import org.restlet.routing.Router;

/**
 * Restlet 案例(二) Filter 的使用
 *
 * 1.Context
 *  1.1 为作为同一容器的一部分的那些 Restlet 实例提供了上下文要素，通常是一个 Component/Application。
 *  1.2 可在 Context 中保存属性/参数, 或访问由容器提供的要素(如 logger, credentials verifiers, etc.)
 *
 * 2.Router
 *  2.1 尝试着匹配请求中的 URI，决定将请求交给哪个 ServerResource/Restlet 实例
 *  2.2 VirtualHost: 特别的 Router，增加了通过域名/IP 等属性匹配虚拟主机的能力
 *
 * @author bascker
 */
public class FilterApplication extends Application {

    public static void main(String[] args) throws Exception {
        final Server server = new Server(Protocol.HTTP);
        server.setNext(new FilterApplication());
        server.start();
    }

    @Override
    public Restlet createInboundRoot() {
        final Context context = getContext();
        final UrlResource resource = new UrlResource(context);
        final UrlFilter filter = new UrlFilter(context);
        filter.setNext(resource);

        final Router router = new Router(context);
        router.attach("http://localhost", resource);
        router.attach("http://localhost/user", resource);

        return filter;
    }
}
