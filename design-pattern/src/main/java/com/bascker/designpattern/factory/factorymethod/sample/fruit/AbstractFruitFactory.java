package com.bascker.designpattern.factory.factorymethod.sample.fruit;

import com.bascker.designpattern.factory.bean.Fruit;

/**
 * AbstractFruitFactory
 *
 * @author bascker
 */
public abstract class AbstractFruitFactory {

    public abstract <T extends Fruit> T getFruit (Class clazz);

}