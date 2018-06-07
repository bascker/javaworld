package com.bascker.restlet.mail.server;

import org.restlet.Component;
import org.restlet.data.Protocol;

/**
 * RESTful 组件包含了 mail 应用
 *
 * @author bascker
 */
public class MailServerComponent extends Component {

    public MailServerComponent() {
        this.setName("RESTful Mail Application");
        this.setDescription("From Rest in Action");
        this.setOwner("org.restlet");
        this.setAuthor("bascker");

        // 添加 HTTP 服务连接器
        getServers().add(Protocol.HTTP, 80);
        // 连接 Application 到默认主机
        getDefaultHost().attachDefault(new MailServerApplication());
    }

    public static void main(String[] args) throws Exception {
        new MailServerComponent().start();
    }

}
