package com.bascker.general.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition Sample: 交替输出：A 输出 1~3 后，交给 B 输出 4~6, 再交给 A 输出 1~9
 *
 * 1.Condition
 *  1.1 条件变量/队列
 *  1.2 很大程度是为了解决 Object 的 wait() 和 notifyAll() 难以使用的问题而产生的
 *
 * 2.创建
 *  2.1 reentrantlock.newCondition()
 *      1) Condition 是与 Lock 绑定的
 *      2) 多个 Condition 需要绑定在同一个 Lock 对象上
 *  2.2 若 Lock 是公平锁，则 Condition 保证线程的唤醒顺序是 FIFO，否则，不保证 FIFO 的唤醒顺序
 *
 * 3.重要方法
 *  3.1 await(): 以原子方式释放相关的锁，并挂起当前线程。用于替代 wait()
 *  3.2 signalAll(): 唤醒所有被挂起的线程
 *
 * 4.应用场景
 *  4.1 用于多线程之间的协调工作
 *  4.2 典型: 阻塞队列 ArrayBlockingQueue 的 take() 和 put() 的实现，就是利用 Condition 实现的
 *
 * @author bascker
 */
public class ConditionSample {

    private static final Logger LOG = LoggerFactory.getLogger(ConditionSample.class);
    private final Lock mLock = new ReentrantLock();
    private final Condition mReachThree = mLock.newCondition();
    private final Condition mReachSix = mLock.newCondition();
    private Thread mThreadA;
    private Thread mThreadB;
    private int mNum = 1;

    public ConditionSample () {}

    public static void main(String[] args) {
        final ConditionSample sample = new ConditionSample();
        sample.start();
    }

    public void start () {
        initThread();
        output();
    }

    private void initThread () {
        mThreadA = new Thread(() -> {
            final String name = Thread.currentThread().getName();
            mLock.lock();
            try {
                // output: 1~3
                while (mNum <= 3) {
                    LOG.info(name + ": " + mNum);
                    mNum ++;
                }
                mReachThree.signalAll();

                // 等待 mReachSix 被触发
                mReachSix.await();
                // output: 6~9
                while (mNum <= 9) {
                    LOG.info(name + ": " + mNum);
                    mNum ++;
                }
            } catch (InterruptedException e) {
                LOG.error(name + ".run", e);
            } finally {
                mLock.unlock();
            }
        });

        mThreadB = new Thread(() -> {
            final String name = Thread.currentThread().getName();
            mLock.lock();
            try {
                // mReachThree 被触发
                mReachThree.await();

                // output: 4~6
                while (mNum <= 6) {
                    LOG.info(name + ": " + mNum);
                    mNum ++;
                }
                mReachSix.signalAll();
            } catch (InterruptedException e) {
                LOG.error(name + ".run", e);
            } finally {
                mLock.unlock();
            }
        });
    }

    private void output () {
        // 先让 B 启动，等待 1s 后启动 A，否则可能 A 运行完 mReachThree.signalAll()，而 B 还没运行，导致 B 一直阻塞
        mThreadB.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            LOG.error("output", e);
        }
        mThreadA.start();
    }

}
