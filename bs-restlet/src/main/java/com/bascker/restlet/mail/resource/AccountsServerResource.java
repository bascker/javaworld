package com.bascker.restlet.mail.resource;

import org.restlet.resource.ServerResource;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Accounts ServerResource
 *
 * @author bascker
 */
public class AccountsServerResource extends ServerResource implements AccountsResource {

    /**
     * 账户列表
     */
    private static final List<String> accounts = new CopyOnWriteArrayList<>();

    @Override
    public String list() {
        final StringBuilder sb = new StringBuilder();
        accounts.forEach(account -> sb.append(account).append("\n"));

        return sb.toString().trim();
    }

    @Override
    public String add(final String account) {
        if (Objects.isNull(account)) {
            throw new IllegalArgumentException("account is invalid");
        }

        accounts.add(account);

        return Integer.toString(accounts.indexOf(account) + 1);
    }

    public static List<String> getAccounts() {
        return accounts;
    }
}
