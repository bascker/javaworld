package com.bascker.general.concurrent.cas;

import com.bascker.bsutil.Sample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * AtomicInteger Sample
 *
 * 1.AtomicInteger
 *  1.1 线程安全的加减操作接口
 *  1.2 无锁实现类，CAS 操作实现并发控制
 *  1.3 底层通过 {@link sun.misc.Unsafe} 来实现: Unsafe 用于执行更新操作时提供"比较并替换"的功能
 *
 * @author bascker
 */
public class AtomicIntegerSample implements Sample {

    private static final Logger LOGGER = LoggerFactory.getLogger(AtomicIntegerSample.class);
    private static final AtomicInteger COUNT = new AtomicInteger(0);

    private Thread th1;
    private Thread th2;

    @Override
    public void start(final Object... args) {
        initThread();

        th1.start();
        th2.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            LOGGER.error("thread sleep failed, cause by {}", e);
        }

        LOGGER.info("COUNT Value: " + COUNT.get());
    }

    public static void main(String[] args) {
        final AtomicIntegerSample sample = new AtomicIntegerSample();
        sample.start();
    }

    private void initThread() {
        final Runnable run = () -> IntStream.range(0, 1000).forEach(i -> COUNT.getAndIncrement());
        th1 = new Thread(run, "thread-01");
        th2 = new Thread(run, "thread-02");
    }

}