package com.bascker.implement.juc;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.stream.IntStream;

/**
 * WaitQueue Unit Test
 *
 * @author bascker
 */
public class WaitQueueTest {

    @Test
    public void test () throws InterruptedException {
        final BlockQueue<Integer> queue = new WaitQueue<>(10);
        new Thread(() -> queue.take(), "th-take").start();
        Thread.sleep(1 * 1000);
        new Thread(() -> IntStream.range(0, 12).forEach(i -> queue.put(i)), "th-put").start();

        Thread.sleep(3 * 1000);
        new Thread(() -> queue.take(), "th-take").start();
        Assert.assertEquals("2 3 4 5 6 7 8 9 10 11", queue.toString());
    }

}
