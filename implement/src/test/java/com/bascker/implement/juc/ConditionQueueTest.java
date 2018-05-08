package com.bascker.implement.juc;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.stream.IntStream;

/**
 * ConditionQueue Unit Test
 *
 * @author bascker
 */
public class ConditionQueueTest {

    private BlockQueue<Integer> queue;

    @Before
    public void before () {
        queue = new ConditionQueue<>(10);
    }

    @Test
    public void test () throws InterruptedException {
        new Thread(() -> queue.take(), "th-take").start();
        new Thread(() -> IntStream.range(0, 12).forEach(i -> queue.put(i)), "th-put").start();

        // 若不再执行一次 take, 则 put 线程将一直阻塞, 由于 queue.size = 10 而无法 put(11) 入 queue
        new Thread(() -> queue.take(), "th-take").start();

        Thread.sleep(3 * 1000);
        Assert.assertEquals("2 3 4 5 6 7 8 9 10 11", queue.toString());
    }

    /**
     * 用于与 test() 对比
     */
    @Test
    public void test2 () throws InterruptedException {
        new Thread(() -> queue.take(), "th-take").start();
        final Thread put = new Thread(() -> IntStream.range(0, 12).forEach(i -> queue.put(i)), "th-put");
        put.start();

        /*
         * test 线程运行完 test() 内部代码后结束，put 中的 LOG 还未来得及输出的,
         * 通过 put.join() 来阻塞 test 线程，让 put 输出 LOG, 但由于 put(11) 无法入队, put 将一直阻塞, test 线程无法终止
         *
         * Q: 为什么 test() 中不需要执行 put.join() 就能获取 LOG 输出?
         * A: 因为执行了 Thread.sleep(3 * 1000), 由于 test 线程休眠了, 此时 put 中的 LOG 可以在 test 线程运行期间得到输出
         */
        put.join();
    }

}
