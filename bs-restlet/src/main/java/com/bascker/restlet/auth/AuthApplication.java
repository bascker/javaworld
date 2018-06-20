package com.bascker.restlet.auth;

import org.restlet.Application;
import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.data.ChallengeScheme;
import org.restlet.routing.Router;
import org.restlet.security.ChallengeAuthenticator;
import org.restlet.security.MapVerifier;

public class AuthApplication extends Application {

    @Override
    public Restlet createInboundRoot() {
        final Context context = getContext();
        final Router router = new Router(context);
        router.attach("/auth", AuthResource.class);
        router.attach("/auth/login", AuthResource.class);

        // 创建认证器
        final ChallengeAuthenticator authenticator = new ChallengeAuthenticator(context, ChallengeScheme.HTTP_BASIC, "LoginRealm");

        // 配置登录用户名/密码
        final MapVerifier verifier = new MapVerifier();
        verifier.getLocalSecrets().put("admin", "admin".toCharArray());
        authenticator.setVerifier(verifier);

        // 将路由器置于认证器之后: 这样请求进来后，会先去进行认证（弹窗），认证成功后才会走路由
        authenticator.setNext(router);

        return authenticator;
    }
}