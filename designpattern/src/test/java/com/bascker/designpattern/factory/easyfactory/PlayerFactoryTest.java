package com.bascker.designpattern.factory.easyfactory;

import com.bascker.designpattern.factory.bean.Player;
import com.bascker.designpattern.factory.easyfactory.sample.player.PlayerFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 * PlayerFactory Unit Test
 *
 * @author bascker
 */
public class PlayerFactoryTest {

    @Test
    public void test () {
        final Player soccer = PlayerFactory.newInstance().build(1);
        final Player basket = PlayerFactory.newInstance().build(0);

        Assert.assertEquals("Soccer", soccer.sport());
        Assert.assertEquals("Basket", basket.sport());
    }

}
