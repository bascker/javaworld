package com.bascker.general.concurrent.pool;

import com.bascker.bsutil.Sample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 *  ThreadPoolExecutor Sample：使用 ThreadPoolExecutor 监控线程池执行状态
 *
 *  1. ThreadPoolExecutor
 *  1.1 线程池核心类
 *  1.2 Executors 创建的线程池都是 ThreadPoolExecutor 实例对象, 是 ExecutorService 的默认实现
 *  1.3 构造函数参数
 *      1) corePoolSize: 线程池核心大小, 即最小线程池大小
 *      2) maximumPoolSize: 最大线程池大小
 *      3) keepAliveTime: 空余线程存活时间，指超过 corePoolSize 的空余线程达到多长时间才销毁
 *      4) unit: 销毁时间单位
 *      5) workQueue: 存储等待执行线程的工作队列, 只会持有通过 execute() 提交的 Runnable 任务
 *      6) threadFactory: 创建线程的工厂，一般用默认 {@link Executors.DefaultThreadFactory} 即可
 *      7) handler: 拒绝策略, 默认为 {@link java.util.concurrent.ThreadPoolExecutor.AbortPolicy}，
 *                  当工作队列、线程池全已满时如何拒绝新任务，默认抛出异常
 *
 * @author bascker
 */
public class ThreadPoolExecutorSample implements Sample {

    private static final Logger LOG = LoggerFactory.getLogger(ThreadPoolExecutorSample.class);
    private ExecutorService mExecutorService;

    public static void main(String[] args) {
        final ThreadPoolExecutorSample sample = new ThreadPoolExecutorSample();
        sample.start();
    }

    @Override
    public void start(final Object... args) {
        init();

        IntStream.range(0, 100000).forEach(i -> mExecutorService.execute(() -> {
            LOG.info(String.valueOf(i));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                LOG.error(e.toString());
            }
        }));

        watch();
    }

    /**
     * 监控
     */
    private void watch () {
        final ThreadPoolExecutor watcher = (ThreadPoolExecutor) mExecutorService;
        while (true) {
            final int queueSize = watcher.getQueue().size();
            LOG.info("当前排队线程数: " + queueSize);

            final int activeCount = watcher.getActiveCount();
            LOG.info("当前活动线程数: " + activeCount);

            final long completedTaskCount = watcher.getCompletedTaskCount();
            LOG.info("执行完毕线程数: " + completedTaskCount);

            final long taskCount = watcher.getTaskCount();
            LOG.info("总线程数: " + taskCount);

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                LOG.info(e.toString());
            }
        }
    }

    private void init () {
        mExecutorService = new ThreadPoolExecutor(50, 100, 0L,
                TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(100000));
    }

}
