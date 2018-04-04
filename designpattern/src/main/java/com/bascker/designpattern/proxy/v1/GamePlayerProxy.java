package com.bascker.designpattern.proxy.v1;

import com.bascker.designpattern.proxy.bean.GamePlayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * GamePlayer Proxy: 游戏代理
 *
 * @author bascker
 */
public class GamePlayerProxy implements GamePlayer {

    private static final Logger LOG = LoggerFactory.getLogger(GamePlayerProxy.class);

    // 被代理对象
    private GamePlayer mGamePlayer;

    // 构造注入
    public GamePlayerProxy(final GamePlayer gamePlayer) {
        mGamePlayer = gamePlayer;
    }

    // -------------------------
    // 代理行为
    // -------------------------
    @Override
    public void login() {
        LOG.info("proxy.login");
        mGamePlayer.login();
    }

    @Override
    public void killBoss() {
        LOG.info("proxy.killBoss");
        mGamePlayer.killBoss();
    }

    @Override
    public void upgrade() {
        LOG.info("proxy.upgrade");
        mGamePlayer.upgrade();
    }
}
