package com.bascker.designpattern.factory.factorymethod.sample.fruit;

/**
 * Banana
 */
public class Banana implements Fruit {

    private static final String NAME = "香蕉";

    @Override
    public String getName() {
        return NAME;
    }

}
