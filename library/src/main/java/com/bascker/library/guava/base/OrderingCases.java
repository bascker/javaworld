package com.bascker.library.guava.base;

import com.bascker.bsutil.bean.Person;
import com.google.common.base.Function;
import com.google.common.collect.Ordering;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    /**
     * reverse(): 获取逆序排序器
     */
    public void testReverse() {
        final Ordering<Integer> natualOrder = Ordering.natural().reverse();
        final List<Integer> list = IntStream.range(0, 10).boxed().collect(Collectors.toList());
        assertEquals("[9, 8, 7, 6, 5, 4, 3, 2, 1, 0]", natualOrder.sortedCopy(list).toString());
    }

    /**
     * nullsFirst(): null 值排在最前
     */
    public void testNullsFirst() {
        final Ordering<Integer> natualOrder = Ordering.natural().nullsFirst();
        final List<Integer> list = IntStream.range(0, 2).boxed().collect(Collectors.toList());
        list.add(null);
        list.add(0);
        list.add(1);
        assertEquals("[null, 0, 0, 1, 1]", natualOrder.sortedCopy(list).toString());
    }

    /**
     * onResultOf(): 对集合中元素调用 Function，在使用 Function 的返回值进行排序
     */
    public void testOnResultOf() {
        final List<Person> persons = Arrays.asList(new Person("bascker"),
                new Person("paul"), new Person("lisa"));
        final Ordering<Person> ordering = Ordering.natural().nullsFirst().onResultOf(new Function<Person, String>() {
            @NullableDecl
            @Override
            public String apply(@NullableDecl Person person) {
                return person.getName();
            }
        });
        assertEquals(Arrays.asList("bascker", "lisa", "paul"), ordering.sortedCopy(persons).stream().map(p -> p.getName()).collect(Collectors.toList()));
    }

}
