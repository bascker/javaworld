package com.bascker.implement.juc;

import com.bascker.bsutil.CollectionHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * WaitQueue: 使用 wait() & notifyAll() 实现阻塞队列
 *
 * 1.WaitQueue VS. ConditionQueue
 *  1.1 实现方式: WaitQueue 使用 wait() & notifyAll() 实现阻塞, ConditionQueue 使用 Condition 实现阻塞
 *  1.2 多条件: ConditionQueue 可以支持多条件, 使用不同的 Condition 对象表示 notEmpty, notFull,
 *      而 WaitQueue 无法使用多个 Object 来表示 notEmpty, notFull, 即如下代码是会出问题的, 会抛出
 *      IllegalMonitorStateException 异常
 *      <p>
 *          private final Object mNotEmpty = new Object();
 *          private final Object mNotFull = new Object();
 *
 *          ...
 *
 *          public void put (T t) {
 *              ...
 *              synchronized (mNotFull) {
 *                  while (...) {
 *                      mNotFull.wait();
 *                  }
 *
 *                  list.add(t);
 *                  mNotEmpty.notifyAll();
 *              }
 *          }
 *
 *          // take(): 类似 put()
 *
 *          // 使用两个线程, 分别执行 put() & take()
 *          public static void main (String[] args) {
 *              final BlockQueue queue = new WaitQueue(10);
 *              new Thread(() -> queue.take(), "th-take").start();
 *              new Thread(() -> IntStream.range(0, 12).forEach(i -> queue.put(i)), "th-put").start();
 *          }
 *      </p>
 *      异常原因: th-take 持有的是 mNotEmpty 的 monitor, 无法去控制在 mNotFull 上等待的 th-put
 *
 * @author bascker
 */
public class WaitQueue<T> implements BlockQueue<T> {

    private static final Logger LOG = LoggerFactory.getLogger(WaitQueue.class);
    private final int mCapacity;
    private final List<T> mList;
    private final Object mLock;

    public WaitQueue (final int capacity) {
        mCapacity = capacity;
        mList = new ArrayList<>(capacity);
        mLock = new Object();
    }

    @Override
    public void put (final T t) {
        if (Objects.isNull(t)) {
            throw new NullPointerException();
        }

        synchronized (mLock) {
            /*
             * 关键: 用 while 不用 if 判断
             * 用 if 的缺陷：当线程 A 被唤醒时，若在线程 A 之前有其他线程执行抢占时间片后，把 mList 填充满了，此时线程 A 就会出问题
             * 用 while 就可以解决 if 的漏洞
             */
            while (mList.size() >= mCapacity) {
                try {
                    LOG.info("queue is full!");
                    mLock.wait();
                } catch (InterruptedException e) {
                    LOG.error(e.getMessage());
                }
            }

            mList.add(t);
            LOG.info("put element " + t + " to queue");
            mLock.notifyAll();
        }
    }

    @Override
    public T take() {
        T t;
        synchronized (mLock) {
            while (mList.isEmpty()) {
                try {
                    LOG.info("queue is empty!");
                    mLock.wait();
                } catch (InterruptedException e) {
                    LOG.error(e.getMessage());
                }
            }

            t = mList.remove(0);
            LOG.info("take element " + t + " from queue");
            mLock.notifyAll();
        }

        return t;
    }

    @Override
    public String toString() {
        return CollectionHelper.toString(mList);
    }
}
