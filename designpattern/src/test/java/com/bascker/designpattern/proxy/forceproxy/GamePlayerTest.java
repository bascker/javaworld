package com.bascker.designpattern.proxy.forceproxy;

import org.junit.Test;

/**
 * GamePlayer Unit Test
 *
 * @author bascker
 */
public class GamePlayerTest {

    private final GamePlayer player = new DnfGamePlayer("bascker", "123456");;

    /**
     * 使用指定代理访问
     */
    @Test
    public void test () {
        // VS. v2.GamePlayer: 强制代理中, 代理对象不需要客户端创建，而是直接从被代理对象中获取
        final GamePlayerProxy proxy = player.getProxy();
        proxy.login();
        proxy.killBoss();
        proxy.upgrade();
    }

    /**
     * 强制代理的问题: 可被绕过
     */
    @Test
    public void testBug () {
        final GamePlayerProxy proxy = player.getProxy();
        player.login();
        player.killBoss();
        player.upgrade();
    }

    /**
     * 直接通过被代理对象访问: 无法执行动作
     */
    @Test
    public void testByPlayer () {
        player.login();
        player.killBoss();
        player.upgrade();
    }

    /**
     * 直接使用代理访问: 无法执行动作
     */
    @Test
    public void testByProxy () {
        final GamePlayerProxy proxy = new GamePlayerProxy(player);
        proxy.login();
        proxy.killBoss();
        proxy.upgrade();
    }

}
