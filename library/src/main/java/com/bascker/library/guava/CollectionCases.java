package com.bascker.library.guava;

import com.bascker.bsutil.CollectionUtils;
import com.google.common.collect.*;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

/**
 * Guava 提供的集合类案例
 * 1.Guava VS. JDK
 *  1.1 简单明了，易于使用
 *  1.2 提供了一系列工厂类方法来支持集合的创建
 *
 * 2.{@link com.google.common.collect.Lists}
 *  2.1 List 工具类
 *
 * 3.Multiset
 *  3.1 Guava 提供了一种新的集合类型, 允许 Set 中同时存在相同的元素
 *  3.2 Multiset 很像 ArrayList，因为它允许重复元素，只不过它的元素之间没有顺序
 *  3.3 具备 Map<String, Integer> 的某一些特性，可以用于进行频率统计
 *  3.4 本质上还是一个真正的集合类型，用来表达数学上“多重集合”的概念
 *  3.5 MultiSet 是无序的，因此只要两个 MultiSet 对象中元素一致，Guava 的 equals() 返回值就为 true
 *
 * 4.Multimap
 *  4.1 Map<K, V> 等价于 Map<K, List<V>> || Map<K, Set<V>>
 *
 * 5.BiMap
 *  5.1 保证值是唯一的，因此 values() 返回 Set 而不是普通的 Collection
 *  5.2 inverse(): 反转 BiMap<K, V> 的键值映射
 *
 * 6.Table
 *  6.1 相当于一个二维表, 有 2 个 key，根据 row 和 column 进行取值
 *  6.2 跟 Map(k, v) 一样 Table(row, column) 对应一个唯一值
 *
 * 7.Sets
 *  7.1 Set 工具类, 提供交/并/补/差等运算
 *  7.2 常用方法:
 *      1) union():                 并集
 *      2) intersection():          交集
 *      3) difference()
 *      4) symmetricDifference()
 *
 * @author bascker
 */
public class CollectionCases {

    private static final Logger LOG = LoggerFactory.getLogger(CollectionCases.class);

    @Test
    public void testLists () {
        final List<String> list = Lists.newArrayList("my", "name", "is", "bascker");
        LOG.info(CollectionUtils.toString(list));
    }

    @Test
    public void testMultiSet () {
        final Multiset<String> multiset = HashMultiset.create();
        multiset.add("a");
        // 添加 5 个 b
        multiset.add("b", 5);
        multiset.addAll(Arrays.asList("my", "name", "is", "bascker", "a", "a", "c"));
        LOG.info(multiset.toString());
        LOG.info("bascker 出现次数: " + multiset.count("bascker"));
        LOG.info("b 出现次数: " + multiset.count("b"));

        final Multiset<Integer> nums = HashMultiset.create();
        nums.addAll(Arrays.asList(1, 2, 3, 3, 4));

        final Multiset<Integer> nums2 = HashMultiset.create();
        nums2.addAll(Arrays.asList(1, 2, 3, 3, 4));
        Assert.assertTrue(nums.equals(nums2));
        Assert.assertFalse(nums == nums2);
    }

    @Test
    public void testMultiMap () {
        final Multimap<String, Integer> multimap = ArrayListMultimap.create();
        // 相当于 Map.put("a", ArrayList(1, 1, 2, 3))
        multimap.put("a", 1);
        multimap.put("a", 1);
        multimap.put("a", 2);
        multimap.put("a", 3);
        multimap.put("b", 4);
        multimap.put("c", 5);

        LOG.info(multimap.keys().toString());
        LOG.info(multimap.asMap().toString());
        LOG.info(CollectionUtils.toString(multimap.get("a")));
        LOG.info(CollectionUtils.toString(multimap.get("b")));
        LOG.info(CollectionUtils.toString(multimap.get("c")));
    }

    @Test
    public void testBiMap () {
        final BiMap<String, Object> biMap = HashBiMap.create();
        biMap.put("name", "bascker");
        biMap.put("age", 24);
        LOG.info(biMap.toString());

        final BiMap<Object, String> inverseBiMap = biMap.inverse();
        LOG.info(inverseBiMap.toString());
    }

    @Test
    public void testTable () {
        final Table<String, String, String> table = HashBasedTable.create();
        table.put("x", "y", "point(x, y)");
        table.put("x", "y", "point(x2, y2)");
        table.put("name", "age", "person(name, age)");

        LOG.info(table.toString());
        LOG.info(table.row("x").toString());
        LOG.info(table.row("name").toString());
        LOG.info(table.column("y").toString());
    }

}
