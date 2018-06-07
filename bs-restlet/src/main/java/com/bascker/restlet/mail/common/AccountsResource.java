package com.bascker.restlet.mail.common;

import org.restlet.resource.Get;
import org.restlet.resource.Post;

/**
 * AccountsResource
 *
 * @author bascker
 */
public interface AccountsResource {

    /**
     * 获取账户列表
     * @return
     */
    @Get
    String list();

    /**
     * 添加账户, 并返回标识
     * @return
     */
    @Post
    String add(final String account);

}
