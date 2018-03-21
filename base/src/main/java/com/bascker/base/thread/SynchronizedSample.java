package com.bascker.base.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * synchronized sample
 *
 * 1.synchronized
 *  1.1 线程安全工具，可保障操作的原子性和内存可见性(不保证线程安全)
 *  1.2 使用: 修饰方法，代码块
 *  1.3 synchronized 是可重入锁. 可重入(re-entrant)表示已获取一个对象的锁的线程，可以多次获取该对象锁，但其他线程必须等待该锁释放
 *  1.4 原理: 每个锁关联一个<b>线程持有者</b>和一个<b>计数器</b>。当计数器为 0 时表示该锁没有被任何线程持有，那么任何线程都都可能获
 *      得该锁而调用相应方法。当一个线程请求成功后，JVM 会记下持有锁的线程，并将计数器计为 1。此时其他线程请求该锁时，发现计数器不为 0，
 *      则必须等待。而该持有锁的线程如果再次请求这个锁，发现该对象计数器不为 0，且锁的持有者正是自己，则允许再次获取该对象锁，并将计数器
 *      递增。当线程退出一个 synchronized 方法/块时，计数器会递减。当计数器为 0 时释放该锁。
 *  1.5 synchronized 是重量级锁, jdk1.6 没进行锁优化前效率较低，1.6+ 锁优化后，效率大大提升
 *  1.6 使用 javap 进行反编译, 可以看到 synchronized 对应 JVM 指令为 monitorenter & monitorexit
 *
 * @author bascker
 */
public class SynchronizedSample {

    private static final Logger LOG = LoggerFactory.getLogger(SynchronizedSample.class);
    private final Object mLock;
    private int mCount = 0;

    public SynchronizedSample () {
        mLock = new Object();
        LOG.info("init count = " + mCount);
    }

    public static void main(String[] args) {
        final SynchronizedSample sample = new SynchronizedSample();
        sample.start();
    }

    public void start () {
        final Thread a = new Thread(() -> synMethod());
        final Thread b = new Thread(() -> synObjectLock());
        final Thread c = new Thread(() -> synClass());

        a.start();
        b.start();
        c.start();
    }

    private synchronized void synMethod () {
        LOG.info("synMethod, count = " + (mCount ++));
    }

    private void synObjectLock () {
        synchronized (mLock) {
            LOG.info("synObjectLock, count = " + (mCount ++));
        }
    }

    public void synClass () {
        synchronized (SynchronizedSample.class) {
            LOG.info("synClass, count = " + (mCount ++));
        }
    }

}
