package com.bascker.restlet.helloworld;

import org.restlet.Server;
import org.restlet.data.Protocol;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

/**
 * Restlet 案例(一) Hello World
 *
 * @author bascker
 */
public class HelloResource extends ServerResource {

    @Get
    public String represent() {
        return "Hello Restlet";
    }

    public static void main(String[] args) throws Exception {
        // 启动一个服务, 使用 HTTP 协议，并监听 8080 端口，所有请求将交给 HelloRestletResource 处理
        final Server server = new Server(Protocol.HTTP, 8080, HelloResource.class);
        server.start();
    }

}
