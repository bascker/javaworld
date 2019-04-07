package com.bascker.bsutil.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class FixedThreadPoolTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(FixedThreadPoolTest.class);

    public static void main(String[] args) throws InterruptedException {
        final List<String> tasks = Arrays.asList("task-01", "task-02", "task-03", "task-04", "task-05");
        final FixedThreadPool threadPool = FixedThreadPool.getInstance(3);
        final ExecutorService service = threadPool.getThreadPool();
        try {
            tasks.forEach(task -> {
                service.submit(() -> {
                    LOGGER.info(Thread.currentThread() + ": deal " + task);
                    try {
                        Thread.sleep(5000L);
                    } catch (InterruptedException e) {
                        LOGGER.error(e.getLocalizedMessage());
                    }
                });
            });
        }
        finally {
            service.shutdown();
            while (!service.awaitTermination(2L, TimeUnit.SECONDS)) {
                LOGGER.warn("some task haven't done, kill the thread pool now");
                service.shutdownNow();
            }
            LOGGER.info("all task have done, kill the thread pool");
        }
    }

}