package com.bascker.base;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Java 移位运算
 * 1.<<: 左移, i << 1 等价于 i * 2
 * 2.>>: 右移, i >> 1 等价于 i / 2
 * 3.>>>: 无符号右移, 忽略符号位，空位以 0 补齐
 *
 * @author bascker
 */
public class Shift {

    private static final Logger LOG = LoggerFactory.getLogger(Shift.class);

    @Test
    public void leftShift() {
        final int i = 1;
        Assert.assertEquals(2, i << 1);
        Assert.assertEquals(4, i << 2);
        Assert.assertEquals(8, i << 3);
        Assert.assertEquals(16, i << 4);
    }

    @Test
    public void rightShift() {
        final int i = -16;
        Assert.assertEquals(-8, i >> 1);
        Assert.assertEquals(-4, i >> 2);
        Assert.assertEquals(-2, i >> 3);
        Assert.assertEquals(-1, i >> 4);

        final int j = 15;
        Assert.assertEquals(j / 2, j >> 1);
        Assert.assertEquals(j / 4, j >> 2);
        Assert.assertEquals(j / 8, j >> 3);
        Assert.assertEquals(j / 16, j >> 4);
    }

    @Test
    public void rightShiftNoSymbol() {
        final int i = -16;
        LOG.info("i >>> 1 = " + String.valueOf(i >>> 1));
        LOG.info("i >>> 2 = " + String.valueOf(i >>> 2));
        LOG.info("i >>> 3 = " + String.valueOf(i >>> 3));
        LOG.info("i >>> 4 = " + String.valueOf(i >>> 4));
    }

}
