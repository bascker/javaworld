package com.bascker.designpattern.factory.abstractfactory.sample.fruit;

/**
 * Small Fruit Factory
 *
 * @author bascker
 */
public class SmallFruitFactory implements FruitFactory {
    @Override
    public Apple getApple() {
        return new SmallApple();
    }

    @Override
    public Banana getBanana() {
        return new SmallBanana();
    }

    @Override
    public Peach getPeach() {
        return new SmallPeach();
    }
}
