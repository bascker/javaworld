package com.bascker.base.thread;

import com.bascker.bsutil.Sample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * wait() sample
 *
 * 1.wait()
 *  1.1 阻塞线程，直到被唤醒。与 sleep() 不同，wait() 会释放该对象的 monitor
 *  1.2 Object 类的 final native 方法, 无法重写
 *  1.3 只能在同步代码中调用，即只能在 synchronized 代码中调用
 *  1.4 要求当前正在运行 object.wait() 方法的线程拥有 object 的 monitor(监视器)，注意不是 lock
 *  1.5 若当前线程不是 monitor 的持有者，则抛出 IllegalMonitorStateException 异常
 *  1.6 调用方式: 3 种
 *      1) wait()
 *      2) this.wait()
 *      3) super.wait()
 *
 * 2.notify(): 随机选择一个在该对象上调用 wait() 的线程，解除其阻塞状态
 * 3.notifyAll(): 解除所有在该对象上调用 wait() 的线程的阻塞状态，注意唤醒的是在<b>同一个对象</b>上进行等待的所有线程
 *
 * @author bascker
 */
public class WaitSample implements Sample {

    private static final Logger LOG = LoggerFactory.getLogger(WaitSample.class);
    private Thread mThread;

    public WaitSample() {}

    public static void main(String[] args) {
        final WaitSample sample = new WaitSample();
        sample.start();
    }

    @Override
    public void start(final Object... args) {
        initThread();
        final long start = System.currentTimeMillis();
        mThread.start();
        try {
            mThread.join();
        } catch (InterruptedException e) {
            LOG.error("start", e);
        }
        final long end = System.currentTimeMillis();
        LOG.info("" + (end - start));
    }

    private void initThread () {
        mThread = new Thread(() -> {
            try {
                new A().show();
            } catch (InterruptedException e) {
                LOG.error("initThread", e);
            }
        }, "thA");
    }

    static class A {

        private synchronized void show () throws InterruptedException {
            LOG.info("A.show: " + Thread.currentThread().getName());
            wait(1000);
        }

    }

}
