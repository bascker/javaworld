package com.bascker.designpattern.factory.factorymethod.sample.fruitfix;

import com.bascker.designpattern.factory.bean.Fruit;
import com.bascker.designpattern.factory.bean.Peach;

/**
 * Peach Factory
 *
 * @author bascker
 */
public class PeachFactory extends AbstractFruitFactory {

    @Override
    public <T extends Fruit> T getFruit() {
        return (T) new Peach();
    }

}
