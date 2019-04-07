package com.bascker.bsutil.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 固定大小、固定阻塞队列长度的线程池, 避免 CacheThreadPool 导致 CPU 超高，以及 FixedThreadPool 无限队列导致内存打满。
 *
 * @author bascker
 * @since v1.0
 */
public class FixedThreadPool {

    private static final Logger LOGGER = LoggerFactory.getLogger(FixedThreadPool.class);

    /**
     * 默认存活时间 60s
     */
    private static final long DEFAULT_ALIVETIME = 60L;

    /**
     * 默认策略：任务队列满员后，阻塞任务，等待任务处理
     */
    private static final RejectedExecutionHandler DEFAULT_HANDLER = (task, executor) -> {
        if (!executor.isTerminated()) {
            try {
                executor.getQueue().put(task);
            } catch (InterruptedException e) {
                LOGGER.error(e.getLocalizedMessage());
            }
        }
    };

    /**
     * 最小线程数: 即核心线程数，不受 aliveTime 控制
     */
    private final int minNum;

    /**
     * 最大线程数
     */
    private final int maxNum;

    /**
     * 存活时间
     */
    private final long aliveTime;

    /**
     * 阻塞队列最大长度
     */
    private final int maxSize;

    /**
     * 拒绝策略
     */
    private final RejectedExecutionHandler handler;

    /**
     * 线程池
     */
    private final ThreadPoolExecutor threadPool;

    private FixedThreadPool(final int minNum, final int maxNum, final int maxSize, final long aliveTime, final RejectedExecutionHandler handler) {
        this.minNum = minNum;
        this.maxNum = maxNum;
        this.maxSize = maxSize;
        this.aliveTime = aliveTime;
        this.handler = handler;
        this.threadPool = new ThreadPoolExecutor(minNum, maxNum, aliveTime, TimeUnit.SECONDS, new ArrayBlockingQueue<>(maxSize), handler);
    }

    public static FixedThreadPool getInstance(int maxNum) {
        return new FixedThreadPool(maxNum, maxNum, maxNum, DEFAULT_ALIVETIME, DEFAULT_HANDLER);
    }

    public static FixedThreadPool getInstance(int maxNum, int maxSize) {
        return new FixedThreadPool(maxNum, maxNum, maxSize, DEFAULT_ALIVETIME, DEFAULT_HANDLER);
    }

    public static FixedThreadPool getInstance(int minNum, int maxNum, int maxSize) {
        return new FixedThreadPool(minNum, maxNum, maxSize, DEFAULT_ALIVETIME, DEFAULT_HANDLER);
    }

    public static FixedThreadPool getInstance(int minNum, int maxNum, int maxSize, long aliveTime, RejectedExecutionHandler handler) {
        return new FixedThreadPool(minNum, maxNum, maxSize, aliveTime, handler);
    }

    public int getMinNum() {
        return minNum;
    }

    public int getMaxNum() {
        return maxNum;
    }

    public long getAliveTime() {
        return aliveTime;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public RejectedExecutionHandler getHandler() {
        return handler;
    }

    public ThreadPoolExecutor getThreadPool() {
        return threadPool;
    }

    @Override
    public String toString() {
        return "FixedThreadPool{" +
                "minNum=" + minNum +
                ", maxNum=" + maxNum +
                ", maxSize=" + maxSize +
                ", handler=" + handler +
                '}';
    }
}