package com.bascker.restlet.mail;

import org.restlet.resource.ClientResource;

import java.io.IOException;

/**
 * Mail Client
 *
 * 1.ClientResource
 *  1.1 客户端资源由 ClientResource 类支持
 *  1.2 可和任何远程的服务器端资源一起工作，以任何技术实现，由类似 HTTP，POP3 或 FTP 的协议定义和一个 Restlet 连接器存在
 *  1.3 提供了类似因为请求失败而重试的策略，一个自动跟随重定向的能力等
 *  1.4 特殊属性
 *      1) retryAttempts: 在报告一个可恢复的错误之前的试图重试的次数，默认值为 2.
 *      2) retryDelay:  在两个试图的重试之间的毫秒级的延迟，默认值为 2s.
 *      3) retryOnError: 当一个可恢复的错误被检测到时幂等的请求（GET/HEAD/OPTIONS）是否应该被获取，默认为 true。
 *      4) followRedirects: 指示重定向是否应该被自动跟随, 默认为 true
 *      5) next: 处理调用的下一个 Restlet, 默认是 context 的 clientDispatcher 属性。若上下文不存在，则基于目标 URI 的方案
 *               协议一个客户端连接器自动实例化。
 *
 * @author bascker
 */
public class MailClient {

    public static void main(String[] args) throws IOException {
        final ClientResource mailClient = new ClientResource("http://localhost/");
        mailClient.get().write(System.out);
    }

}
