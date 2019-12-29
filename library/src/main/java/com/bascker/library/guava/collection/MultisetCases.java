package com.bascker.library.guava.collection;

import com.google.common.collect.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.testng.Assert.assertEquals;

/**
 * MultiSet 案例
 * 1. Guava 提供了一种新的集合类型, 允许 Set 中同时存在相同的元素
 * 2. Multiset 很像 ArrayList，因为它允许重复元素，只不过它的元素之间没有顺序
 * 3. 具备 Map<E, Integer> 的某一些特性，可以用于进行频率统计: key 为元素值， value 为计数
 * 4. 本质上还是一个真正的集合类型，用来表达数学上“多重集合”的概念
 * 5. MultiSet 是无序的，因此只要两个 MultiSet 对象中元素一致，Guava 的 equals() 返回值就为 true
 * 6. Multiset 的实现类
 *  6.1 {@link HashMultiset}: 支持 null，底层实现为 HashMap
 *  6.2 {@link TreeMultiset}: 支持 null，底层实现为 TreeMap
 *  6.3 {@link com.google.common.collect.LinkedHashMultiset}: 支持 null, 底层实现为 LinkedHashMap
 *  6.4 {@link com.google.common.collect.ConcurrentHashMultiset}: 不支持 null, 底层实现为 ConcurrentHashMap
 *  6.5 {@link com.google.common.collect.ImmutableMultiset}: 不支持 null, 底层实现为 ImmutableSet
 * @author bascker
 */
@Test
public class MultisetCases {

    private static final Logger LOGGER = LoggerFactory.getLogger(MultisetCases.class);

    private Multiset<Integer> numSet;

    @BeforeTest
    private void init() {
        LOGGER.info("MultiSetCases init start");
        numSet = HashMultiset.create();

        numSet.add(1, 1);
        numSet.add(2, 4);
        numSet.add(3, 9);
        LOGGER.info("MultiSetCases init end");
    }

    /**
     * 创建 Multiset
     */
    public void testCreate() {
        final Multiset<String> hashMultiset = HashMultiset.create();
        hashMultiset.add("a");
        hashMultiset.add("b", 5);           // 添加 5 个 b
        hashMultiset.addAll(Arrays.asList("my", "name", "is", "bascker", "a", "a", "c", "b", null));
        LOGGER.info("hashMultiset = {}, bascker.num = {}, b.num = {}",
                hashMultiset.toString(),
                hashMultiset.count("bascker"),
                hashMultiset.count("b"));

        assertEquals(1, hashMultiset.count("bascker"));
        assertEquals(6, hashMultiset.count("b"));
        assertEquals(1, hashMultiset.count(null));
    }

    /**
     * count(E): 统计元素 E 在集合中的个数
     */
    public void testCount() {
        assertEquals(4, numSet.count(1));
    }

    /**
     * elementSet(): 返回不重复元素的集合
     */
    public void testElementSet() {
        final Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        assertEquals(set, numSet.elementSet());
    }

    /**
     * entrySet(): 和Map的entrySet类似，返回Set<Multiset.Entry<E>>，其中包含的Entry支持getElement()和getCount()方法
     */
    public void testEntrySet() {
        Multiset<String> persons = HashMultiset.create();
        persons.add("bascker", 2);
        persons.add("john");
        persons.add("lisa", 2);

        for (Multiset.Entry entry : persons.entrySet()) {
            LOGGER.info("element = {}, count = {}", entry.getElement(), entry.getCount());
        }
    }

    /**
     * size(): 返回集合总个数（包括重复元素）
     */
    public void testSize() {
        assertEquals(14, numSet.size());
    }

    /**
     *  {@link SortedMultiset} 是 Multiset 的变种, 支持高效地获取指定范围的子集
     */
    public void testSortedMultiset() {
        final SortedMultiset<Integer> sortedMultiset = TreeMultiset.create();
        sortedMultiset.addAll(numSet);
        assertEquals("[1, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3]", Arrays.toString(sortedMultiset.toArray()));
        // 统计 x 属于 [0, 2] 这个区间内的元素
        assertEquals("[1, 2 x 4]", sortedMultiset.subMultiset(0, BoundType.CLOSED, 2, BoundType.CLOSED).toString());
    }

}
