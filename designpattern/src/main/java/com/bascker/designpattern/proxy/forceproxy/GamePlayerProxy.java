package com.bascker.designpattern.proxy.forceproxy;

/**
 * GamePlayer Proxy
 *
 * @author bascker
 */
public class GamePlayerProxy implements GamePlayer {

    private GamePlayer mGamePlayer;

    public GamePlayerProxy(final GamePlayer gamePlayer) {
        mGamePlayer = gamePlayer;
    }

    @Override
    public GamePlayerProxy getProxy() {
        return this;
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
