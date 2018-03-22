package com.bascker.base.thread;

import com.bascker.bsutil.Sample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * interrupt() sample: 线程循环输出 1~9, 当数值为 6 时，中断线程
 *
 * 1.interrupt()
 *  1.1 线程中断：中断是一个状态，线程会不时地检测中断标识位，以判断线程是否应该被中断
 *  1.2 调用 thread.interrupt() 并不能立即引发中断，只是设置了JVM内部的中断标记，线程会自己在合适的时机触发中断
 *  1.3 调用 Thread.interrupted() 可以检测线程中断状态
 *  1.4 线程中断 != 线程终止
 *
 * 2.QA
 *  2.1 volatile: 若 mCount 不修饰为 volatile, 则且 while 中进行输出 LOG.info(), 则很大程度上程序会进入死循环
 *      mCount 修饰为 volatile, 则线程会在 i = 6 时被打断，跑出异常
 *  2.2 为什么 mCount = 6 时，有时被打断后 isInterrupted 输出为 false? mCount = 6 时，调用 mThread.interrupt()
 *      将其中断标记置为 true, 但设为 true 之前，可能已经执行输出 isInterrupted 了，而当线程检测到标记为 true 执行
 *      中断操作时，才进入到 sleep() 调用，因此输出为 false
 *
 * @author bascker
 */
public class InterruptSample implements Sample {

    private static final Logger LOG = LoggerFactory.getLogger(InterruptSample.class);
    private Thread mThread;
    private volatile int mCount = 0;

    public InterruptSample() {}

    public static void main(String[] args) {
        final InterruptSample sample = new InterruptSample();
        sample.start();
    }

    @Override
    public void start (final Object... args) {
        initThread();
        mThread.start();
        while (true) {
            if (mCount == 6) {
                mThread.interrupt();
                return;
            }
        }
    }

    private void initThread () {
        mThread = new Thread(() -> {
            final String name = Thread.currentThread().getName();
            for (int i = 1; i < 10; i ++) {
                mCount = i;
                try {
                    Thread.sleep(1000);
                    LOG.info(name + " , i = " + i + ", isInterrupted = " + Thread.interrupted());
                } catch (InterruptedException e) {
                    LOG.error(name, e);
                }
            }
        });
    }

}
