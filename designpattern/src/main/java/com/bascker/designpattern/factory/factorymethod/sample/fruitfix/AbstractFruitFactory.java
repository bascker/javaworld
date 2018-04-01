package com.bascker.designpattern.factory.factorymethod.sample.fruitfix;

import com.bascker.designpattern.factory.bean.Fruit;

/**
 * AbstractFruitFactory: 该版本的 getFruit 中已经不需要传递 Class clazz 参数了, 因为每个具体工厂都已经非常明确了自己的职责
 *
 * @see com.bascker.designpattern.factory.factorymethod.sample.fruit.AbstractFruitFactory
 * @author bascker
 */
public abstract class AbstractFruitFactory {

    public abstract <T extends Fruit> T getFruit ();

}
