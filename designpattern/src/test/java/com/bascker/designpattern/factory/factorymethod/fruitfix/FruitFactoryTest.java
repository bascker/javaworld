package com.bascker.designpattern.factory.factorymethod.fruitfix;

import com.bascker.designpattern.factory.bean.Apple;
import com.bascker.designpattern.factory.bean.Banana;
import com.bascker.designpattern.factory.bean.Peach;
import com.bascker.designpattern.factory.factorymethod.sample.fruitfix.AbstractFruitFactory;
import com.bascker.designpattern.factory.factorymethod.sample.fruitfix.AppleFactory;
import com.bascker.designpattern.factory.factorymethod.sample.fruitfix.BananaFactory;
import com.bascker.designpattern.factory.factorymethod.sample.fruitfix.PeachFactory;
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
        final AbstractFruitFactory appleFactory = new AppleFactory();
        final Apple apple = appleFactory.getFruit();
        Assert.assertEquals("苹果", apple.getName());

        final AbstractFruitFactory bananaFactory = new BananaFactory();
        final Banana banana = bananaFactory.getFruit();
        Assert.assertEquals("香蕉", banana.getName());

        final AbstractFruitFactory peachFactory = new PeachFactory();
        final Peach peach = peachFactory.getFruit();
        Assert.assertEquals("桃子", peach.getName());
    }

}