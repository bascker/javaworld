package com.bascker.designpattern.singleton;

/**
 * EagerSingleton
 *
 * 1.饿汉式单例
 *  1.1 类加载时，唯一的实例就已被实例化
 *
 * @author bascker
 */
public class EagerSingleton {

    private static EagerSingleton mInstance = new EagerSingleton();

    public static EagerSingleton getInstance() {
        return mInstance;
    }

    private EagerSingleton() {}
}
