package com.bascker.restlet.mail;

import com.bascker.restlet.mail.resource.RootResource;
import org.restlet.resource.ClientResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MailProxyClient: ClientResource 返回注解接口的代理示例
 *
 * @author bascker
 */
public class MailProxyClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailProxyClient.class);

    public static void main(String[] args) {
        final RootResource rootResource = ClientResource.create("http://localhost/", RootResource.class);
        // 直接调用 represent() 透明地发起一个 GET 请求调用远程的 RootServerResource 实现这个同样的注解的 Java 方法
        final String represent = rootResource.represent();
        LOGGER.info("represent is: {}", represent);
    }

}
