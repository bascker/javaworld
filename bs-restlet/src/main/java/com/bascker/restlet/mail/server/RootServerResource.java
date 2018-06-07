package com.bascker.restlet.mail.server;

import com.bascker.restlet.mail.common.RootResource;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Root ServerResource: 使用注解来处理 GET/OPTIONS 等请求
 *
 * 1.ServerResource
 *  1.1 特定属性
 *      1) Negotiated: 是否支持内容协商。内容协商是 HTTP 的一个强大的特性，可使你为同样的资源定义几种表示变量并且基于客户端的偏好
 *         自动选择最佳的
 *      2) Existing: 指示由这个 ServerResource 的实例标识的资源是否真的存在。默认情况为 true。
 *
 *
 * @author bascker
 * @version 1.0.1
 */
public class RootServerResource extends ServerResource implements RootResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(RootServerResource.class);

    @Override
    public String represent() {
        return  "Welcome to " + this.getApplication().getName() + "!";
    }

    @Override
    public String describe() {
        throw new RuntimeException("Not Provided");
    }

    @Override
    protected void doCatch(final Throwable throwable) {
        LOGGER.error("Catch an exception", throwable);
    }

    @Override
    protected void doInit() throws ResourceException {
        LOGGER.info("start doInit, init RootServerResource");
    }

    @Override
    protected void doRelease() throws ResourceException {
        LOGGER.info("start doRelease, release RootServerResource");
    }
}
