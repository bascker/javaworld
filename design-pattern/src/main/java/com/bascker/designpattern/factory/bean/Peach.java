package com.bascker.designpattern.factory.bean;

/**
 * Peach
 *
 * @author bascker
 */
public class Peach implements Fruit {

    private static final String NAME = "桃子";

    @Override
    public String getName() {
        return NAME;
    }

}
