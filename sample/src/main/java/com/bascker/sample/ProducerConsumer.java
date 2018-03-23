package com.bascker.sample;

import com.bascker.bsutil.CollectionUtils;
import com.bascker.bsutil.Sample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 生产者-消费者问题: 阻塞队列的典型案例
 *
 * @author bascker
 */
public class ProducerConsumer implements Sample {

    private static final Logger LOG = LoggerFactory.getLogger(ProducerConsumer.class);

    public static void main(String[] args) {
        final ProducerConsumer sample = new ProducerConsumer();
        sample.start();
    }

    @Override
    public void start(final Object... args) {
        final BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(100);
        final long start = System.currentTimeMillis();

        final NumberProducer producer = new NumberProducer(queue);
        new Thread(producer, "Producer").start();
        final NumberConsumer consumer = new NumberConsumer(queue);
        new Thread(consumer, "Consumer").start();

        long end = System.currentTimeMillis();
        while (end - start < (10 * 1000)) {
            end = System.currentTimeMillis();
        }
        consumer.shutdown();
        producer.shutdown();
    }

    /**
     * 生产者
     */
    static class NumberProducer implements Runnable {

        private final BlockingQueue<Integer> mQueue;
        private volatile boolean mShutdown;

        public NumberProducer (final BlockingQueue<Integer> queue) {
            mQueue = queue;
            mShutdown = false;
        }

        @Override
        public void run() {
            while (!mShutdown) {
                try {
                    final int i = nextNumber();
                    LOG.info("Producer.put: " + i);
                    mQueue.put(i);
                    Thread.sleep(2 * 1000);
                } catch (InterruptedException e) {
                    LOG.error("NumberProducer.run", e);
                }
            }
        }

        public void shutdown () {
            mShutdown = true;
        }

    }

    /**
     * 消费者
     */
    static class NumberConsumer implements Runnable {

        private final BlockingQueue<Integer> mQueue;
        private volatile boolean mShutdown;

        public NumberConsumer(final BlockingQueue<Integer> queue) {
            mQueue = queue;
            mShutdown = false;
        }

        @Override
        public void run() {
            while (!mShutdown) {
                try {
                    Thread.sleep(3 * 1000);
                    LOG.info("Queue: " + CollectionUtils.toString(mQueue) + ", Consumer.Take: " + mQueue.take());
                } catch (InterruptedException e) {
                    LOG.error("NumberConsumer.run", e);
                }
            }
        }

        public void shutdown () {
            mShutdown = true;
        }
    }

    /**
     * 随机生成 [0, 100) 的数字
     * @return
     */
    private static int nextNumber () {
        final Random random = new Random();
        return random.nextInt(100);
    }

}