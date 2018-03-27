package com.bascker.implement.juc;

import com.bascker.bsutil.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ConditionQueue: 使用 Condition 来实现阻塞队列
 * Note: 自实现阻塞队列的问题, 京东面试考过
 *
 * @author bascker
 */
public class ConditionQueue<T> implements BlockQueue<T> {

    private static final Logger LOG = LoggerFactory.getLogger(ConditionQueue.class);

    // 最大元素容纳量
    private final int mCapacity;
    private final List<T> mList;
    private final ReentrantLock mLock;
    private final Condition mNotEmpty;
    private final Condition mNotFull;

    public ConditionQueue (final int capacity) {
        mCapacity = capacity;
        mList = new ArrayList<>(capacity);
        mLock = new ReentrantLock();
        mNotEmpty = mLock.newCondition();
        mNotFull = mLock.newCondition();
    }

    @Override
    public void put(final T t) {
        if (Objects.isNull(t)) {
            throw new NullPointerException();
        }

        final ReentrantLock lock = mLock;
        lock.lock();
        try{
            // 关键点: while() 判断
            while (mList.size() >= mCapacity) {
                LOG.info("queue is full!");
                mNotFull.await();
            }

            mList.add(t);
            LOG.info("put element " + t.toString() + " to queue");
            mNotEmpty.signalAll();
        } catch (InterruptedException e) {
            LOG.error(e.getMessage());
        } finally {
            lock.unlock();
        }
    }

    @Override
    public T take() {
        T t = null;
        final ReentrantLock lock = mLock;
        lock.lock();
        try {
            // 关键点: while() 判断
            while (mList.isEmpty()) {
                LOG.info("queue is empty!");
                mNotEmpty.await();
            }

            t = mList.remove(0);
            LOG.info("take element " + t.toString() + " from queue");
            mNotFull.signalAll();
        } catch (InterruptedException e) {
            LOG.error(e.getMessage());
        } finally {
            lock.unlock();
        }

        return t;
    }

    @Override
    public String toString () {
        return CollectionUtils.toString(mList);
    }
}
