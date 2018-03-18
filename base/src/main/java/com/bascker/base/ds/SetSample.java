package com.bascker.base.ds;

import com.bascker.bsutil.CollectionUtils;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Set 集合
 * 1.Set 原理: 基于 Map 实现, 如 Map 是 key 不能重复，Set 则是元素不能重复，因此 Set 就是利用 Map 中“键”不能重复的特性来实现的
 * 2.Set 子类
 * 2.1 HashSet 和 TreeSet：不按照元素添加循序存放
 * 2.2 LinkedHashSet：保持元素的添加顺序
 * 2.3 TreeSet：对 Set 中的元素进行排序存放
 * 3.一般说来，先把元素添加到 HashSet，再把集合转换为 TreeSet 来进行有序遍历会更快-->这点和HashMap的使用非常的类似
 *
 * @author bascker
 */
public class SetSample {

    private static final Logger LOG = LoggerFactory.getLogger(SetSample.class);
    private static Set<String> set = new HashSet<>();

    @Before
    public void before() {
        set.addAll(Arrays.asList("123", "234", "Hello", "world", "bascker"));
    }

    /**
     * 基础
     */
    @Test
    public void base() {
        // 遍历元素：第一种方式使用迭代器
        final Iterator<String> iterator = set.iterator();
        final StringBuffer sb = new StringBuffer();
        while (iterator.hasNext()) {
            sb.append(iterator.next() + " ");
        }
        sb.delete(sb.length() - 1, sb.length());
        LOG.info("By iterator: " + sb.toString());

        // 遍历元素：第二种方式使用 for 循环
        sb.delete(0, sb.length());
        for (String s : set) {
            sb.append(s + " ");
        }
        sb.delete(sb.length() - 1, sb.length());
        LOG.info("By forEach: " + sb.toString());

        LOG.info("size: " + set.size());
        LOG.info("isEmpty: " + set.isEmpty());
        LOG.info("remove 123: " + set.remove("123"));
        final List<String> list = Arrays.asList("234", "Hello");
        set.removeAll(list);
        LOG.info("移除list后的大小：" + set.size());
        set.clear();
        LOG.info("After clear(), size: " + set.size());
    }

    /**
     * 利用 Set 去除重复，然后按字母顺序输出
     */
    @Test
    public void removeRepeat() {
        final List<String> list = Arrays.asList("1", "2", "4", "4", "3",
                "2", "1", "5", "6", "7",
                "10", "8", "12", "11", "9");
        Set<String> datas = new HashSet<>();
        datas.addAll(list);
        // 1. HashSet 可以去重，但是不保证内部元素有序，即内部元素是无序的
        LOG.info(CollectionUtils.toString(datas));

        // 2. TreeSet：可以去重，以及按元素的自然顺序存储-->注意:其内排序对于字符和数字的区别
        datas = new TreeSet<>();
        datas.addAll(list);
        LOG.info(CollectionUtils.toString(datas));

        final List<Integer> nums = Arrays.asList(1, 3, 5, 4, 2, 10, 8, 36, 24, 12, 7, 0);
        Set<Integer> set = new TreeSet<>();
        set.addAll(nums);
        LOG.info(CollectionUtils.toString(set));

        // 3. LinkedHashSet：保持元素的添加顺序
        set = new LinkedHashSet<>();
        set.addAll(nums);
        LOG.info(CollectionUtils.toString(nums));
    }

}
