package com.bascker.designpattern.factory.abstractfactory.sample.fruit;

/**
 * Big Fruit Factory
 *
 * @author bascker
 */
public class BigFruitFactory implements FruitFactory {

    @Override
    public Apple getApple() {
        return new BigApple();
    }

    @Override
    public Banana getBanana() {
        return new BigBanana();
    }

    @Override
    public Peach getPeach() {
        return new BigPeach();
    }
}
