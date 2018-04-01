package com.bascker.designpattern.factory.factorymethod.sample.fruitfix;

import com.bascker.designpattern.factory.bean.Apple;
import com.bascker.designpattern.factory.bean.Fruit;

/**
 * Apple Factory
 *
 * @author bascker
 */
public class AppleFactory extends AbstractFruitFactory {

    @Override
    public <T extends Fruit> T getFruit() {
        return (T) new Apple();
    }

}
