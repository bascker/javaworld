package com.bascker.designpattern.factory.factorymethod.sample.fruit;

/**
 * Apple
 *
 * @author bascker
 */
public class Apple implements Fruit {

    private static final String NAME = "苹果";

    @Override
    public String getName() {
        return NAME;
    }

}
