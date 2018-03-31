package com.bascker.designpattern.singleton;

import java.util.Objects;

/**
 * volatile + DCL(双检测锁定) 实现懒汉式单例，解决 LazySample 高并发下效率问题
 *
 * @author bascker
 */
public class DclSingleton {

    // 使用 volatile 保证内存可见性
    private static volatile DclSingleton mInstance = null;

    public static DclSingleton getInstance () {
        if (Objects.isNull(mInstance)) {
            // 同步控制: 持有类锁, 确保 mInstance 只会被初始化一次
            synchronized (DclSingleton.class) {
                // 若实例真的不存在, 才创建
                if (Objects.isNull(mInstance)) {
                    mInstance = new DclSingleton();
                }
            }
        }

        return mInstance;
    }

    private DclSingleton() {}

}
