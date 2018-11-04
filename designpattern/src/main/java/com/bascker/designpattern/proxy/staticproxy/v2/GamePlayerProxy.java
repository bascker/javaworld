package com.bascker.designpattern.proxy.staticproxy.v2;

import com.bascker.designpattern.proxy.bean.GamePlayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Game Player Proxy
 *
 * @author bascker
 */
public class GamePlayerProxy implements GamePlayer {

    private static final Logger LOG = LoggerFactory.getLogger(GamePlayerProxy.class);
    private GamePlayer mGamePlayer;

    /**
     * VS. v1.GamePlayerProxy: 只需要知道被代理对象的 username, password 即可进行代理
     * @param username
     * @param password
     */
    public GamePlayerProxy(final String username, final String password) {
        try {
            mGamePlayer = new DnfGamePlayer(this, username, password);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public void login() {
        mGamePlayer.login();
    }

    @Override
    public void killBoss() {
        mGamePlayer.killBoss();
    }

    @Override
    public void upgrade() {
        mGamePlayer.upgrade();
    }
}
