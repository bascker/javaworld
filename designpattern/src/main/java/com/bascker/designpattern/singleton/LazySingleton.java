package com.bascker.designpattern.singleton;

import java.util.Objects;

/**
 * LazySingleton
 *
 * 1.懒汉式单例
 *  1.1 懒汉式 VS. 饿汉式: 对象实例化时间点不同，饿汉式在一开始就实例化，而懒汉式只有真正需要该实例 getInstance() 时才创建
 *  1.2 缺点: 每次调用 getInstance() 都需要进行同步，高并发下低效
 *
 * @author bascker
 */
public class LazySingleton {

    private static LazySingleton mInstance = null;

    public static synchronized LazySingleton getInstance () {
        if (Objects.isNull(mInstance)) {
            mInstance = new LazySingleton();
        }

        return mInstance;
    }

    private LazySingleton () {}

}
