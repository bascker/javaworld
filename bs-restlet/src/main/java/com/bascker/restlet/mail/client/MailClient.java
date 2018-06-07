package com.bascker.restlet.mail.client;

import com.bascker.restlet.mail.common.AccountResource;
import com.bascker.restlet.mail.common.AccountsResource;
import com.bascker.restlet.mail.common.RootResource;
import org.restlet.Client;
import org.restlet.Context;
import org.restlet.data.Protocol;
import org.restlet.resource.ClientResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static final Logger LOGGER = LoggerFactory.getLogger(MailClient.class);

    public static void main(String[] args) throws IOException {
        // 使用 getChild() 为子资源创建客户端代理

        LOGGER.info("1) Set up the service client resource\n");
        final Client client = new Client(new Context(), Protocol.HTTP);
        final ClientResource service = new ClientResource("http://localhost/");
        service.setNext(client);

        LOGGER.info("\n2) Display the root resource\n");
        final RootResource mailService = service.getChild("/", RootResource.class);
        LOGGER.info(mailService.represent());

        LOGGER.info("\n3) Display the initial list of accounts\n");
        final AccountsResource mailAccounts = service.getChild("/accounts/", AccountsResource.class);
        final String list = mailAccounts.list();
        LOGGER.info(list == null ? "<empty>\n" : list);

        LOGGER.info("4) Adds new accounts\n");
        mailAccounts.add("Homer Simpson");
        mailAccounts.add("Marjorie Simpson");
        mailAccounts.add("Bart Simpson");

        LOGGER.info("\n5) Display the updated list of accounts\n");
        LOGGER.info(mailAccounts.list());

        LOGGER.info("6) Display the second account\n");
        AccountResource mailAccount = service.getChild("/accounts/1", AccountResource.class);
        LOGGER.info(mailAccount.query());

        LOGGER.info("\n7) Update the individual account and display it again\n");
        mailAccount.store("Marge Simpson");
        LOGGER.info(mailAccounts.list());

        LOGGER.info("\n8) Delete the first account and display the list again\n");
        mailAccount = service.getChild("/accounts/0", AccountResource.class);
        mailAccount.remove();
        LOGGER.info(mailAccounts.list());
    }

}
