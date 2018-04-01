package com.bascker.designpattern.factory.easyfactory;

import com.bascker.designpattern.factory.bean.Apple;
import com.bascker.designpattern.factory.bean.Banana;
import com.bascker.designpattern.factory.bean.Peach;
import com.bascker.designpattern.factory.easyfactory.sample.fruit.FruitFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 * FruitFactory Unit Test
 *
 * @author bascker
 */
public class FruitFactoryTest {

    @Test
    public void test () throws InstantiationException, IllegalAccessException {
        final Apple apple = FruitFactory.getFruit(Apple.class);
        Assert.assertEquals("苹果", apple.getName());

        final Banana banana = FruitFactory.getFruit(Banana.class);
        Assert.assertEquals("香蕉", banana.getName());

        final Peach peach = FruitFactory.getFruit(Peach.class);
        Assert.assertEquals("桃子", peach.getName());
    }

}
