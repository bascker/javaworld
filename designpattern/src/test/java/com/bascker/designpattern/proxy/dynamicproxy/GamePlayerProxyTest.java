package com.bascker.designpattern.proxy.dynamicproxy;

import com.bascker.designpattern.proxy.bean.DnfGamePlayer;
import com.bascker.designpattern.proxy.bean.GamePlayer;
import org.junit.Test;

/**
 * GamePlayer Proxy Unit Test
 *
 * @author bascker
 */
public class GamePlayerProxyTest {

    @Test
    public void test () {
        final GamePlayer player = new DnfGamePlayer("bascker", "123456");
        final GamePlayerProxy handler = new GamePlayerProxy(player);
        final GamePlayer proxy = (GamePlayer) handler.getProxy();
        proxy.login();
        proxy.killBoss();
        proxy.upgrade();
    }

}
