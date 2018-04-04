package com.bascker.designpattern.proxy.v2;

import com.bascker.designpattern.proxy.bean.GamePlayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * DNF Game Player
 *
 * @author bascker
 */
public class DnfGamePlayer implements GamePlayer {

    private static final Logger LOG = LoggerFactory.getLogger(DnfGamePlayer.class);
    private String mUserName;
    private String mPassword;

    /**
     * VS. v1.DnfGamePlayer: 通过构造注入，限制谁能代理 DnfGamePlayer
     * @param player
     * @param userName
     * @param password
     * @throws Exception
     */
    public DnfGamePlayer(final GamePlayer player, final String userName, final String password) throws Exception {
        if (Objects.isNull(player)) {
            throw new Exception("无法创建真实角色");
        }

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
