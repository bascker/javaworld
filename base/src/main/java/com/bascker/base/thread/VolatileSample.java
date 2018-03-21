package com.bascker.base.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.IntStream;

/**
 * volatile sample
 *
 * 1.volatile
 *  1.1 保障了内存可见性, 不保证操作的原子性
 *  1.2 Java 多线程模型包括<b>主内存(main memory)</b>, <b>线程栈(working memory)</b>, 线程在处理数据时，会将值从 main memory
 *      中 load 出来，存入自己本地 working memory 中(为了加快速度)，完成操作后再将新的值 save 到 main memory。这个操作过程不是
 *      原子性操作, 因此会出现线程问题中典型的"过期数据"的问题
 *  1.3 volatile 解决了 1.2 所产生的问题，它保证线程对共享变量的操作激发一次完整的 load and save
 *  1.4 原理：防止 JVM 指令重排序
 *  1.5 volatile 不能与 final 一起使用
 *  1.6 用法: 只能修饰变量
 *
 * @author bascker
 */
public class VolatileSample {

    private static final Logger LOG = LoggerFactory.getLogger(VolatileSample.class);
    // 利用 volatile 保障 reader 看到的 mNums 中的值都是最新的
    private volatile List<Integer> mNums;
    private volatile int mCount;
    private Thread mWriter;
    private Thread mReader;
    private Runner mRunner;

    public VolatileSample () {
        mNums = new ArrayList<>();
        mCount = 0;
    }

    public static void main(String[] args) {
        final VolatileSample sample = new VolatileSample();
        sample.start(2);
    }

    public void start (final int sampleId) {
        initThread();

        if (sampleId != 1) {
            startSample2();
            return;
        }

        startSample1();
    }

    private void startSample1 () {
        mWriter.start();
        mReader.start();
    }

    private void startSample2 () {
        mRunner.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            LOG.error("startSample2", e);
        }
        mRunner.setRun(false);
    }

    private void initThread () {
        mWriter = new Thread(() -> IntStream.range(1, 16).forEach(i -> {
            mNums.add(i);
            mCount ++;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                LOG.error("Writer", e);
            }
        }));
        mReader = new Thread(() -> {
            while (mWriter.isAlive()) {
                LOG.info("reader: ["
                        + "get: " + (mNums.isEmpty() ? null : mNums.get(mNums.size() - 1))
                        + ", "
                        + "nums: " + mNums
                        + ", "
                        + "count: " + mCount
                        + "]");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    LOG.error("Reader", e);
                }
            }
        });

        mRunner = new Runner();
    }

    static class Runner extends Thread {

        private boolean mRun = true;

        @Override
        public void run() {
            while (mRun) {
                LOG.info("Runner, run at " + Calendar.getInstance().getTimeInMillis());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    LOG.error("Runner.run", e);
                }
            }

            LOG.info("Runner, stop at " + Calendar.getInstance().getTimeInMillis());
        }

        public boolean isRun() {
            return mRun;
        }

        public void setRun(final boolean run) {
            mRun = run;
        }
    }

}
