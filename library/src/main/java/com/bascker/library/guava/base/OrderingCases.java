package com.bascker.library.guava.base;

import com.google.common.collect.Ordering;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Ordering 排序器的使用
 *
 * @author bascker
 */
@Test
public class OrderingCases {

    /**
     * 1. natural(): 创建一个对可排序类型做自然排序（数字按大小排序，日期按先后排序）的排序器
     * 2. sortedCopy(): 对可排序类型进行排序，并返回排序结果
     */
    public void testNatural() {
        final Ordering<Integer> ordering = Ordering.natural();
        final List<Integer> nums = Arrays.asList(1, 3, 2, 8, 6, 0, -1);
        assertEquals("[-1, 0, 1, 2, 3, 6, 8]", ordering.sortedCopy(nums).toString());
        assertEquals(Integer.valueOf(8), ordering.max(nums));
    }

    /**
     * usingToString(): 按对象的字符串形式做字典排序
     */
    public void testUsingToString() {
        final Ordering<String> naturalOrder = Ordering.natural();
        final Ordering<Object> utsOrder = Ordering.usingToString();

        final List<String> names = Arrays.asList("lisa", "bascker", "harry", "json", "paul", "john");
        assertEquals("[bascker, harry, john, json, lisa, paul]", naturalOrder.sortedCopy(names).toString());
        assertEquals("[bascker, harry, john, json, lisa, paul]", utsOrder.sortedCopy(names).toString());
    }

}
