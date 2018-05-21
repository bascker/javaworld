package com.bascker.restlet.helloworld;

import org.restlet.*;
import org.restlet.data.MediaType;
import org.restlet.data.Protocol;

/**
 * Restlet 案例(一) Hello World
 *
 * 1.Application
 *  1.1 通常用于开发完整的 web 应用
 *
 * 2.Restlet
 *  2.1 实现了 Uniform 接口并且提供了与 javax.servlet.http.HttpServlet 类等价的东西
 *  2.2 Restlet VS. HttpServlet
 *      1) Restlet 抽象了 HTTP 的底层细节, 如 HTTP 头的解析
 *      2) 线程安全，可同时处理多个请求
 *
 * @author bascker
 */
public class HelloApplication extends Application {

    public static void main(String[] args) throws Exception {
        // 若不指定端口号, 则使用协议的默认端口号
        final Server server = new Server(Protocol.HTTP, HelloResource.class);
        server.setNext(new HelloApplication());
        server.start();
    }

    @Override
    public Restlet createInboundRoot() {
        // 重写 handle 方法来处理请求，返回响应
        return new Restlet() {
            @Override
            public void handle(final Request request, final Response response) {
                final StringBuilder sb = new StringBuilder();
                sb.append("data{").append("lib: ").append("restlet, ")
                    .append("version: ").append("2.3.12}");

                response.setEntity(sb.toString().trim(), MediaType.TEXT_PLAIN);
            }
        };
    }
}
