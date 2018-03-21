package com.bascker.base.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * yield() sample
 *
 * 1.yield()
 *  1.1 让权, 当前线程释放时间片，进入 Runnable 状态，重新和其他处于 Runnable 状态的线程一起抢占 CPU 时间片，谁抢到，谁执行
 *  1.2 是 native 方法
 *  1.3 能使线程从 Running 变为 Runnable
 *  1.4 优先级和 yield 关系：不是优先级越高，就一定会抢到释放出来的 CPU 时间，而是概率大一点而已
 *
 * @author bascker
 */
public class YieldSample {

    private static final Logger LOG = LoggerFactory.getLogger(YieldSample.class);
    private final Runnable mAction;
    private Thread mTh1;
    private Thread mTh2;

    public YieldSample () {
        mAction = () -> {
            final String threadName = Thread.currentThread().getName();
            for (int i = 0; i < 5; i ++) {
                LOG.info(threadName + ": " + i);
                // 当 i = 3 时，释放 CPU 时间，然后当前线程和其他线程一起抢时间片，谁抢到谁执行
                if (i == 3) {
                    LOG.info(threadName + " 让出了时间片");
                    Thread.yield();
                    LOG.info(threadName + " 抢到了时间片");
                }
            }
        };
    }

    public static void main(String[] args) {
        final YieldSample sample = new YieldSample();
        sample.start();
    }

    public void start () {
        initThread();
        mTh1.start();
        mTh2.start();
    }

    private void initThread () {
        mTh1 = new Thread(mAction, "th1");
        mTh2 = new Thread(mAction, "th2");
        mTh1.setPriority(Thread.MAX_PRIORITY);
        mTh2.setPriority(Thread.MIN_PRIORITY);
    }

}
