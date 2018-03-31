package com.bascker.designpattern.factory;

import com.bascker.designpattern.factory.factorymethod.sample.fruit.*;
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
        final AbstractFruitFactory fruitFactory = new FruitFactory();
        final Apple apple = fruitFactory.getFruit(Apple.class);
        Assert.assertEquals("苹果", apple.getName());

        final Banana banana = fruitFactory.getFruit(Banana.class);
        Assert.assertEquals("香蕉", banana.getName());

        final Peach peach = fruitFactory.getFruit(Peach.class);
        Assert.assertEquals("桃子", peach.getName());
    }

}