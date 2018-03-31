package com.bascker.designpattern.factory.factorymethod.sample.fruit;

/**
 * AbstractFruitFactory
 *
 * @author bascker
 */
public abstract class AbstractFruitFactory {

    public abstract <T extends Fruit> T getFruit (Class clazz);

}