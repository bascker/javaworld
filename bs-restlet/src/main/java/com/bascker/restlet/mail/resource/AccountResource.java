package com.bascker.restlet.mail.resource;

import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;

/**
 * AccountResource
 *
 * @author bascker
 */
public interface AccountResource {

    /**
     * 获取账户表示
     * @return
     */
    @Get
    String query();

    /**
     * 更新账户
     * @param account
     */
    @Put
    void store(final String account);

    /**
     * 删除指定账户
     */
    @Delete
    void remove();

}
