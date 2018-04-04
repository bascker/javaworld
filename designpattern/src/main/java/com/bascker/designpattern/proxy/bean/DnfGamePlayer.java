package com.bascker.designpattern.proxy.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DNF Game Player
 *
 * @author bascker
 */
public class DnfGamePlayer implements GamePlayer {

    private static final Logger LOG = LoggerFactory.getLogger(DnfGamePlayer.class);
    private String mUserName;
    private String mPassword;

    public DnfGamePlayer(final String userName, final String password) {
        mUserName = userName;
        mPassword = password;
    }

    @Override
    public void login() {
        LOG.info(mUserName + " 登录 DNF");
    }

    @Override
    public void killBoss() {
        LOG.info(mUserName + " 杀怪");
    }

    @Override
    public void upgrade() {
        LOG.info(mUserName + " 升级");
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(final String userName) {
        mUserName = userName;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(final String password) {
        mPassword = password;
    }
}
