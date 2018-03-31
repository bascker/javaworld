package com.bascker.designpattern.singleton;

import java.util.Objects;

/**
 * volatile + DCL(Double Check Lock，双检测锁定) 实现懒汉式单例，解决 LazySample 高并发下效率问题
 *
 * 1.需要使用 volatile 修饰 mInstance, 以解决 DCL 的问题
 * <p>
 *     // 不给 mInstance 实例修饰 volatile 时 DCL 的问题
 *     public class DclSingleton {
 *
 *         private static DclSingleton mInstance = null;
 *         private int mNumber;
 *
 *         public static DclSingleton getInstance () {
 *             // ...保持不变
 *         }
 *
 *         public void getNumber {
 *             return mNumber;
 *         }
 *
 *        private DclSingleton () {
 *            mNumber = 100;
 *        }
 *     }
 *
 *     DCL 问题: 多线程情况下，调用 getNumber() 时，返回值可能是 0.
 *     原因: 在 getInstance() 的 if (Objects.isNull(mInstance)) 判断时，线程可能看到已经存在但尚未完全初始化(不完整)的实例.
 *          即当线程 B 在执行 new DclSingle() 但内部的 mNumber 还未初始化为 100 时, 线程 A 开始执行 if 判断, 此时 A 会认为
 *          mInstance 已经存在了，故而在调用 getNumber() 时，可能返回的是默认值 0.
 * </p>
 *
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
