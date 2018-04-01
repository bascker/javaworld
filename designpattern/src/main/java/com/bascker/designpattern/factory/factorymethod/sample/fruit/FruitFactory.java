package com.bascker.designpattern.factory.factorymethod.sample.fruit;

import com.bascker.designpattern.factory.bean.Fruit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FruitFactory
 *
 * @author bascker
 */
public class FruitFactory extends AbstractFruitFactory {

    private static final Logger LOG = LoggerFactory.getLogger(FruitFactory.class);

    @Override
    public <T extends Fruit> T getFruit (Class clazz) {
        Fruit fruit = null;
        try {
            fruit = (Fruit) clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            LOG.error(e.getMessage());
        }

        return (T) fruit;
    }

}
