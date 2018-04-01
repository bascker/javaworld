package com.bascker.designpattern.factory.factorymethod.sample.singleton;

/**
 * Emperor: 皇帝, 单例
 *
 * @author bascker
 */
public class Emperor {

    private String mName;

    public String getName () {
        return mName;
    }

    public void setName(final String name) {
        mName = name;
    }

    private Emperor () {}

}
