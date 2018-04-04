package com.bascker.designpattern.proxy.forceproxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * DNF GamePlayer
 *
 * @author bascker
 */
public class DnfGamePlayer implements GamePlayer {

    private static final Logger LOG = LoggerFactory.getLogger(DnfGamePlayer.class);
    private GamePlayerProxy mProxy;
    private String mUserName;
    private String mPassword;

    public DnfGamePlayer(final String userName, final String password) {
        mUserName = userName;
        mPassword = password;
    }

    @Override
    public GamePlayerProxy getProxy() {
        mProxy = new GamePlayerProxy(this);
        return mProxy;
    }

    @Override
    public void login() {
        if (isProxy()) {
            LOG.info(mUserName + " 登录 DNF");
        } else {
            LOG.warn("请调用 getProxy() 获取指定代理，并用代理访问!");
        }
    }

    @Override
    public void killBoss() {
        if (isProxy()) {
            LOG.info(mUserName + " 杀怪");
        } else {
            LOG.warn("请调用 getProxy() 获取指定代理，并用代理访问!");
        }
    }

    @Override
    public void upgrade() {
        if (isProxy()) {
            LOG.info(mUserName + " 升级");
        } else {
            LOG.warn("请调用 getProxy() 获取指定代理，并用代理访问!");
        }
    }

    private boolean isProxy () {
        // 只要调用过 getProxy() 获取指定代理后，才会返回 true
        return Objects.nonNull(mProxy);
    }

}
