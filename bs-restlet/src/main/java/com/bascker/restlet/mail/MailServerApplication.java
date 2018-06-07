package com.bascker.restlet.mail;


import com.bascker.restlet.mail.resource.AccountServerResource;
import com.bascker.restlet.mail.resource.RootServerResource;
import org.restlet.Application;
import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.Server;
import org.restlet.data.Protocol;
import org.restlet.routing.Router;

/**
 * RESTful 邮件系统
 *
 * @author bascker
 */
public class MailServerApplication extends Application {

    public MailServerApplication() {
        // name & description & owner & author 都是 Restlet 超类的属性
        this.setName("RESTful Mail Application");
        this.setDescription("From Rest in Action");
        this.setOwner("org.restlet");
        this.setAuthor("bascker");
    }

    public static void main(String[] args) throws Exception {
        final Application application = new MailServerApplication();
        final Server mailServer = new Server(Protocol.HTTP, 80);
        mailServer.setNext(application);
        mailServer.start();
    }

    /**
     * 创建 Root Restlet 以跟踪请求
     * @return
     */
    @Override
    public Restlet createInboundRoot() {
        final Context context = getContext();

        // 定义一个路由: 指定某一请求 URL 指定使用哪个过滤器, API 最后的 "/" 一定要加上, 否则抛出 404 异常
        final Router router = new Router(context);
        router.attach("http://localhost/", RootServerResource.class);
        router.attach("http://localhost/accounts/", AccountServerResource.class);
        router.attach("http://localhost/accounts/{accountId}", AccountServerResource.class);

        return router;
    }
}
