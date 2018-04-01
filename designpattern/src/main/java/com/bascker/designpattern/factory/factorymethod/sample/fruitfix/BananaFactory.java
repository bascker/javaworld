package com.bascker.designpattern.factory.factorymethod.sample.fruitfix;

import com.bascker.designpattern.factory.bean.Banana;
import com.bascker.designpattern.factory.bean.Fruit;

/**
 * Banana Factory
 *
 * @author bascker
 */
public class BananaFactory extends AbstractFruitFactory {

    @Override
    public <T extends Fruit> T getFruit() {
        return (T) new Banana();
    }

}
