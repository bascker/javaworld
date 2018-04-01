package com.bascker.designpattern.factory.bean;

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
