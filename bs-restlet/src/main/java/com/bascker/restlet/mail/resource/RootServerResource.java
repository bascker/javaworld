package com.bascker.restlet.mail.resource;

import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Root ServerResource：通过重写 get/options 等方法来处理 GET/OPTIONS 等请求.
 *
 * 1.ServerResource
 *  1.1 特定属性
 *      1) Negotiated: 是否支持内容协商。内容协商是 HTTP 的一个强大的特性，可使你为同样的资源定义几种表示变量并且基于客户端的偏好
 *         自动选择最佳的
 *      2) Existing: 指示由这个 ServerResource 的实例标识的资源是否真的存在。默认情况为 true。
 *
 *
 * @author bascker
 */
public class RootServerResource extends ServerResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(RootServerResource.class);

    public RootServerResource() {
        // 禁止内容协商
        this.setNegotiated(false);
    }

    @Override
    protected void doCatch(final Throwable throwable) {
        LOGGER.error("Catch an exception", throwable);
    }

    /**
     * 处理 GET 方法返回简单的文本表示
     * @return
     * @throws ResourceException
     */
    @Override
    protected Representation get() throws ResourceException {
        LOGGER.info("start get");
        return new StringRepresentation("This is RootServerResource");
    }

    /**
     * 处理 OPTIONS 方法说明一个异常的抛出的影响
     * @return
     * @throws ResourceException
     */
    @Override
    protected Representation options() throws ResourceException {
        LOGGER.info("start options");
        throw new RuntimeException("Not provided");
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
