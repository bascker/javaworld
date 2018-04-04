package com.bascker.designpattern.proxy.v1;

import com.bascker.designpattern.proxy.bean.DnfGamePlayer;
import org.junit.Test;

/**
 * GamePlayer Unit Test
 *
 * @author bascker
 */
public class GamePlayerTest {

    @Test
    public void test () {
        final DnfGamePlayer player = new DnfGamePlayer("bascker", "123456");
        final GamePlayerProxy proxy = new GamePlayerProxy(player);
        proxy.login();
        proxy.killBoss();
        proxy.upgrade();
    }

}
