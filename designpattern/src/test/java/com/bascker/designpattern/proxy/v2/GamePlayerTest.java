package com.bascker.designpattern.proxy.v2;

import org.junit.Test;

/**
 * GamePlayer Unit Test
 *
 * @author bascker
 */
public class GamePlayerTest {

    @Test
    public void test () {
        // VS. v1.GamePlayerTest: 调用时，客户端无需创建被代理对象，只需传入代理对象的数据即可
        final GamePlayerProxy proxy = new GamePlayerProxy("bascker", "123456");
        proxy.login();
        proxy.killBoss();
        proxy.upgrade();
    }

}
