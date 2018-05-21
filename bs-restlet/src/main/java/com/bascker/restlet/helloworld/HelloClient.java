package com.bascker.restlet.helloworld;

import org.restlet.resource.ClientResource;

import java.io.IOException;

/**
 * Restlet 案例(一) Hello World
 *
 * 1.ClientResource
 *  1.1 其使用有点类似 HttpClient 的集合体
 *  1.2 实际上, 它由资源的标识实例化(如 URI)，类似 ServerResource 的一个本地代理
 *  1.3 允许对同一目标资源调用一系列的方法，自动重定向并在发生网络错误时自动重发请求
 *
 * @author bascker
 */
public class HelloClient {

    public static void main(String[] args) throws IOException {
        // 通过客户端方式, 发送 GET 请求获取信息
        final ClientResource client = new ClientResource("http://localhost:8080");
        // write() 将 GET 请求后的相应输出到标准输出
        client.get().write(System.out);
    }

}
