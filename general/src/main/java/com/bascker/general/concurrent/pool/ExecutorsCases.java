package com.bascker.general.concurrent.pool;

import com.bascker.bsutil.DateUtils;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Executors Cases
 *
 * 1.Executors
 *  1.1 工厂类, 用于创建线程池
 *  1.2 可创建 5 种线程池:
 *      1) FixedThreadPool & CachedThreadPool & SingleThreadExecutor & ScheduledThreadPool
 *      2) WorkStealingPool: JDK 1.8+ 提供
 *  1.3 线程池好处: 实现线程复用, 节省系统资源
 *
 * 2.FixedThreadPool
 *  2.1 容量固定的线程池, 每当提交一个任务就创建一个线程，直到到达线程池最大数量
 *  2.2 可用于控制最大并发数，超出的线程会在同步队列中等待，以此来减轻服务器压力
 *  2.3 阻塞队列使用 LinkedBlockingQueue, 是一个无界队列
 *
 * 3.CachedThreadPool
 *  3.1 内存足够时, 可无限扩充容量的线程池
 *  3.2 适合处理执行时间比较小的任务: 可重用现有线程，不会由于创建新线程延迟任务执行，提高响应
 *  3.3 采用 SynchronousQueue 装等待的任务: SynchronousQueue 没有存储空间, 意味着只要有请求到来，就必须要找到一条工作线程处理，
 *      若当前没有空闲的线程，就会再创建一条新的线程.
 *
 * 4.SingleThreadExecutor
 *  4.1 只会创建一条工作线程处理任务, 保证所有任务按照指定顺序(FIFO, LILO, priority)执行
 *  4.2 阻塞队列使用 LinkedBlockingQueue
 *  4.3 适合处理固定任务, 如执行监控任务
 *
 * 5.ScheduledThreadPool
 *  5.1 固定容量的线程池, 可用于处理延时任务或定时任务
 *  5.2 任务执行方式:
 *      1) scheduledAtFixedRate:     定时执行, 每隔 period 时间后运行一次任务
 *      2) scheduledWithFixedDelay:  延时执行, 上一次任务成功执行后，延迟 delay 时间后再次执行
 *  5.3 采用 DelayQueue 存储等待的任务
 *
 * 6.WorkStealingPool
 *  6.1 拥有多个任务队列（以便减少连接数）的线程池
 *  6.2 底层返回的是 ForkJoinPool 实例
 *
 * @see ExecutorService                             真正的线程池接口
 * @see java.util.concurrent.ThreadPoolExecutor
 *
 * @author bascker
 */
public class ExecutorsCases {

    private static final Logger LOG = LoggerFactory.getLogger(ExecutorsCases.class);
    private Runnable action;

    @Before
    public void before () {
        action = () -> LOG.info(Thread.currentThread().getName() + ", time: " + DateUtils.now());
    }

    @Test
    public void testFixedThreadPool () {
        // 容量为 3 的池子
        final ExecutorService fixThreadPool = Executors.newFixedThreadPool(3);
        try {
            IntStream.range(0, 10).forEach(i -> fixThreadPool.execute(action));
            Thread.sleep(1 * 1000);
        } catch (InterruptedException e) {
            LOG.error(e.getMessage());
        } finally {
            fixThreadPool.shutdown();
        }
    }

    @Test
    public void testCachedThreadPool () {
        final ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        try {
            IntStream.range(0, 10).forEach(i -> {
                cachedThreadPool.execute(action);
                try {
                    Thread.sleep(1 * 100);
                } catch (InterruptedException e) {
                    LOG.error(e.getMessage());
                }
            });
        } finally {
            cachedThreadPool.shutdown();
        }
    }

    @Test
    public void testSingleThreadExecutor () {
        final ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
        try {
            singleThreadPool.execute(action);
            Thread.sleep(1 * 1000);
        } catch (InterruptedException e) {
            LOG.error(e.getMessage());
        } finally {
            singleThreadPool.shutdown();
        }
    }

    @Test
    public void testScheduledThreadPool () {
        final ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);
        try {
            // 延迟执行: 只执行 1 次
            scheduledThreadPool.schedule(action, 1, TimeUnit.SECONDS);

            /*
             * 周期性任务:
             * 1.第一次延迟 1s 后执行, 之后每 3s 执行一次，即 1s + 3s -> 1s + 2 * 3s -> 1s + 3 * 3s
             * 2.若某一个任务遇到异常，则下一次的任务执行将会被延后，否则只能通过取消或者结束 executor 才能终止任务
             * 3.若某一个任务的时间消耗 > period，则后面的任务将会等待该任务执行完毕，而不是并发执行
             * 4.period: 延时的固定频率
             */
            scheduledThreadPool.scheduleAtFixedRate(action, 1, 3, TimeUnit.SECONDS);

            /*
             * 周期性任务:
             * 1.第一次延迟 2s 后执行, 之后每个任务成功执行后, 再延迟 3s 执行下一个任务,
             *   即 2s + costTime(task1) -> 2s + costTime(task1) + 3s -> 2s + costTime(task1) + 3s + costTime(task2) + 3s
             * 2.任务执行遇到异常的情况，同 scheduleAtFixedRate()
             * 3.delay: 上一次任务成功执行后，等待多久开始执行下一次任务
             */
            scheduledThreadPool.scheduleWithFixedDelay(action, 2, 3, TimeUnit.SECONDS);

            Thread.sleep(100 * 1000);

        } catch (InterruptedException e) {
            LOG.error(e.getMessage());
        } finally {
            scheduledThreadPool.shutdown();
        }
    }

    @Test
    public void testWorkStealingPool () throws InterruptedException {
        // 设置并行线程数为 2, 即每时每刻只有 2 个线程同时执行, 若不设置，则默认是当前系统的 CPU 个数
        final ExecutorService workStealingThreadPool = Executors.newWorkStealingPool(2);
        try {
            IntStream.range(0, 50).forEach(i -> workStealingThreadPool.execute(action));
        } finally {
            workStealingThreadPool.shutdown();
        }

        Thread.sleep(1 * 100);
    }

}
