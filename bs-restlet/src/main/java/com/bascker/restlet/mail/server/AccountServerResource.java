package com.bascker.restlet.mail.server;

import com.bascker.restlet.mail.common.AccountResource;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

/**
 * Account ServerResource
 *
 * @author bascker
 */
public class AccountServerResource extends ServerResource implements AccountResource {

    private int accountId;

    @Override
    public String query() {
        return AccountsServerResource.getAccounts().get(accountId - 1);
    }

    @Override
    public void store(final String account) {
        AccountsServerResource.getAccounts().set(accountId - 1, account);
    }

    @Override
    public void remove() {
        AccountsServerResource.getAccounts().remove(accountId - 1);
    }

    @Override
    protected void doInit() throws ResourceException {
        // 从 request 中获取 accountId 值
        this.accountId = Integer.parseInt((String) getRequestAttributes().get("accountId"));
    }
}
