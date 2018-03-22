package com.bascker.implement;

import com.bascker.bsutil.Sample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * 使用 wait() 和 notifyAll() 实现栅栏功能: synchronized + wait() + notifyAll() 是典型的生产者-消费者方案的解决方式
 */
public class BarrierSample implements Sample {

    private static final Logger LOG = LoggerFactory.getLogger(BarrierSample.class);
    // 充当对象锁
    private final Object mLock;
    private final String[] mNames;
    private Integer mThreadNum;

    public BarrierSample(final String[] names) {
        mLock = new Object();
        mNames = names;
        mThreadNum = names.length;
    }

    public static void main(String[] args) {
        final BarrierSample sample = new BarrierSample(new String[]{"bascker", "jerry", "john", "paul", "lisa", "jessica"});
        sample.start();
    }

    @Override
    public void start(final Object... args) {
        IntStream.range(0, mNames.length).forEach(i -> new Thread(new Aboard(mNames[i])).start());
    }

    class Aboard implements Runnable {

        private final String mName;

        public Aboard(final String name) {
            mName = name;
        }

        @Override
        public void run() {
            try {
                final Random random = new Random();
                final int costTime = random.nextInt(3) + 1;
                final String threadName = Thread.currentThread().getName();
                Thread.sleep(costTime * 1000);
                LOG.info(threadName + " " + mName + "\t花了" + (costTime * 10) +  "分钟到达出发点");

                synchronized (mLock) {
                    // 获取 mLock 对象锁
                    mThreadNum --;

                    if (Objects.equals(0, mThreadNum)) {
                        // 唤醒所有在 mLock 上等待的线程
                        mLock.notifyAll();
                        LOG.info(threadName + "所有同事已到达，大家快上车，准备开车了!");

                        return;
                    }

                    // 释放 mLock 对象锁，进入 waiting 状态
                    mLock.wait();
                    LOG.info(threadName + " " + mName + "\t已上车");
                }

            } catch (InterruptedException e) {
                LOG.error("Aboard.run", e);
            }
        }
    }
}
