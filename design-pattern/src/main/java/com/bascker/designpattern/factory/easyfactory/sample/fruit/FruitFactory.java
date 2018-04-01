package com.bascker.designpattern.factory.easyfactory.sample.fruit;

import com.bascker.designpattern.factory.bean.Fruit;

/**
 * FruitFactory
 *
 * @author bascker
 */
public class FruitFactory {

    /**
     * 静态工厂方法
     * @param clazz
     * @param <T>
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <T extends Fruit> T getFruit (Class clazz) throws IllegalAccessException, InstantiationException {
        return (T) clazz.newInstance();
    }

    private FruitFactory () {}

}
