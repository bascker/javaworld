package com.bascker.base.ds;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Set;

/**
 * BitSet Sample
 * 1.用于按位存储(0/1)，下标表示元素值，元素使用 true/false 表示是否存在
 * 2.是存储海量数据的一种方式
 * 3.BitSet 是非线程安全的数据结构，因此多线程场景下，需要使用同步手段来保证线程安全性
 * 4.海量数据场景：
 *  4.1 寻找海量数据中的重复元素
 *  4.2 去除海量数据中的重复元素
 *  4.3 判断某个数是否在海量数据中有出现
 *
 * @author bascker
 */
public class BitSetSample {

    private static final Logger LOG = LoggerFactory.getLogger(BitSetSample.class);

    /**
     * 基础使用
     */
    @Test
    public void base() {
        // 默认构造一个 64 位的 BitSet, 每一位初始值都是 false
        BitSet bitSet = new BitSet();
        LOG.info("size: " + bitSet.size());
        LOG.info("empty: " + bitSet.isEmpty());

        // 下标从 0 开始
        LOG.info("0: " + bitSet.get(0));
        // 当使用的位数超过了默认 size(64) 时，会再次申请 64 位，而不是报错
        LOG.info("64: " + bitSet.get(64));

        // 设置下标为 10 的位置为 true
        bitSet.set(10);
        LOG.info("10: " + bitSet.get(10));
    }

    /**
     * BitSet 存储数组
     * 1.原始存储一个数组的方式
     *  1.1 一个 int 占 4 个字节，则 nums 这个数组会占据 4 * 4 = 16 字节
     *  1.2 缺点：大数据情况下，会消耗大量存储空间和内存
     *
     * 2.BitSet 方式: 可大大降低空间消耗
     */
    @Test
    public void storeIntArr() {
        final int[] nums = {1, 3, 6, 9};
        LOG.info(ArrayUtils.toString(nums));

        /*
         * 利用如下步骤使用 BitSet 来降低空间消耗, 通过这种方式，本来需要用 1byte 的空间来存储的数字，
         * 只需要用 1bit 的空间就可以表示大大降低了空间消耗
         */
        // 1.找出 nums 中的最大数
        final int max = 9;
        // 2.设置 BitSet 大小为 10
        BitSet bitSet = new BitSet(max + 1);
        // 3.设置每个要存储数的值为 true
        bitSet.set(1);
        bitSet.set(3);
        bitSet.set(6);
        bitSet.set(9);
        final StringBuffer sb = new StringBuffer();
        // 4.输出数组
        for (int i = 0, len = bitSet.size(); i < len; i++) {
            if (bitSet.get(i)) {
                sb.append(i);
            }
        }
        sb.delete(sb.length() - 1, sb.length());
        LOG.info(sb.toString());
    }

    /**
     * BitSet 的运用：海量数据中找出重复的数据
     * 场景:
     *  目前有 1000W 个以 8 开头的 QQ 号，每个 QQ 号的长度为 8(如 81234567)。
     *  给你一台普通 PC，内存为 2G，请你找出所有重复的电话号码，要求时间复杂度尽量小
     *
     * 分析：
     *  1000W 个长度为 8 的 QQ 号，对于普通 PC 而言，是无法一次性加载到内存中的，因此我们使用 BitSet 进行按位存储。
     *  我们除去首位的元素 8，然后构建一个大小为 10000000 的 BitSet 即可，将所有 QQ 号映射到该 BitSet 中。
     *  该 BitSet 消耗的内存空间大小 = 10000000 / 8 / 1024 / 1024 = 1.192M，很轻松的可以映射除去首尾 8 的所有 QQ 号
     */
    @Test
    public void findRepeat() {
        // 模拟海量的 QQ 号
        final String[] qqNums = {"81234567", "81134567", "81224567", "81233567", "81234467",
                "82234567", "81134567", "81224567", "81777777", "81234467",
                "82234567", "81134567", "81222567", "81777777", "81234467",
                "81334567", "81134567", "81222267", "81666667", "81234467",
                "81233567", "81134567", "81222267", "81666657", "81234467",
                "81234467", "81134567", "81222567", "81656667", "81234467"};

        // BitSet: 所有从 0 开始
        final int SIZE = 10000000 + 1;
        final BitSet bitSet = new BitSet(SIZE);

        // 映射: 模拟逐个读取 QQ 号
        final Set<String> repeats = new HashSet<>();
        Arrays.stream(qqNums).forEach(s -> {
            // 除去首尾的 8
            final String sub = s.substring(1);
            final int num = Integer.valueOf(sub);
            if (bitSet.get(num)) {
                repeats.add(s);
            } else {
                bitSet.set(num);
            }
        });

        // 显示所有重复 QQ 号
        LOG.info(ArrayUtils.toString(repeats));
    }

    @Test
    public void testIsContains() {
        isContains(1);
        isContains(41);
        isContains(111);
    }

    /**
     * BitSet 的运用：判断给出的元素 n 是否出现在海量数据中
     * 场景：
     *  一台普通 PC，其内存为 2G，要求处理一个包含 40 亿个不重复、无序的无符号 int 型整数。
     *  给出一个整数 n，如何快速判断 n 是否在这 40 亿个数据中?    ==> 也可以用 BitSet 做
     *
     * 分析：
     *  40 亿个 int 元素，其总大小 = 40亿 * 4 / 1024 / 1024 / 1024 = 14.9G, 因此该 PC 无法一次性加载整个数据。
     *  若按 BitSet 的思想，使用 bit 位来标识一个 int 整数，则 40 亿的 int 数据消耗的空间大小 = 40亿 / 8(转byte) / 1024 / 1024 = 476.83M,
     *  这样存储空间就大大减少了，完全可以将 40 亿数据全部加载入内存。
     *
     * 具体思路:
     *  只需申请一个 Integer.MAX_VALUE(2147483647)大小的 BitSet，
     *  其消耗的内存大小 = 2147483647 / 8 / 1024 / 1024 = 256M
     *
     * @param n
     */
    private void isContains(final int n) {
        // 模拟海量数据
        final int[] nums = {1, 0, 24, 33, 25,
                80, 90, 11, 2, 3,
                4, 10000, 20000, 11111111, 245678910,
                10, 12, 13, 41, 45, 34,
                Integer.MAX_VALUE};

        // BitSet
        final BitSet bitSet = new BitSet(Integer.MAX_VALUE);
        Arrays.stream(nums).forEach(bitSet::set);

        if (bitSet.get(n)) {
            LOG.info("元素 " + n + " 是否在海量数据中 ? " + true);
        } else {
            LOG.info("元素 " + n + " 是否在海量数据中 ? " + false);
        }
    }

}
