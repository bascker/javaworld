package com.bascker.designpattern.singleton;

/**
 * 延迟加载 singleton
 *
 * 1.Lazy initialization holder class 模式
 *  1.1 既有饿汉式的效率，也有懒汉式的延迟加载
 *
 * @author bascker
 */
public class DelaySingleton {

    public static DelaySingleton getInstance () {
        return DelaySingleton.getInstance();
    }

    static class DelaySingletonHolder {
        private static final DelaySingleton mInstance = new DelaySingleton();
    }

    private DelaySingleton () {}

}
