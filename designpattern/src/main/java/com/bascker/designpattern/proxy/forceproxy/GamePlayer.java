package com.bascker.designpattern.proxy.forceproxy;

/**
 * GamePlayer
 *
 * @author bascker
 */
public interface GamePlayer {

    /**
     * 寻找自己的代理，指定要访问自己必须通过哪个代理
     * @return
     */
    GamePlayerProxy getProxy ();

    void login();

    void killBoss();

    void upgrade();

}
