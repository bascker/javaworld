package com.bascker.designpattern.factory.abstractfactory;

import com.bascker.designpattern.factory.abstractfactory.sample.fruit.*;
import org.junit.Assert;
import org.junit.Test;

/**
 * FruitFactory Unit Test
 *
 * @author bascker
 */
public class FruitFactoryTest {

    @Test
    public void test () {
        // 生成大个水果的生产线
        final FruitFactory bigFactory = new BigFruitFactory();
        final Apple bigApple = bigFactory.getApple();
        final Banana bigBanana = bigFactory.getBanana();
        final Peach bigPeach = bigFactory.getPeach();
        Assert.assertEquals("Apple", bigApple.getName());
        Assert.assertEquals("Big", bigApple.getSize());
        Assert.assertEquals("Banana", bigBanana.getName());
        Assert.assertEquals("Big", bigBanana.getSize());
        Assert.assertEquals("Peach", bigPeach.getName());
        Assert.assertEquals("Big", bigPeach.getSize());

        // 生成小个水果的生产线
        final FruitFactory smallFactory = new SmallFruitFactory();
        final Apple smallApple = smallFactory.getApple();
        Assert.assertEquals("Apple", smallApple.getName());
        Assert.assertEquals("Small", smallApple.getSize());
    }

}
