package com.bascker.designpattern.proxy.bean;

/**
 * GamePlayer: 游戏玩家, 抽象主题角色
 *
 * @author bascker
 */
public interface GamePlayer {

    void login ();

    /**
     * 打怪
     */
    void killBoss ();

    /**
     * 升级
     */
    void upgrade ();

}
