package com.bascker.library.guava.collection;

import com.bascker.bsutil.CollectionHelper;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Map;

import static org.testng.Assert.assertEquals;

/**
 * Multimap 案例
 * 1. Map<K, V> 等价于 Map<K, List<V>> || Map<K, Set<V>>
 *
 * @author bascker
 */

@Test
public class MultimapCases {

    private static final Logger LOGGER = LoggerFactory.getLogger(MultimapCases.class);

    private Multimap<String, Integer> multimap;

    /**
     * create(): Multimap 的创建
     */
    @BeforeTest
    public void testCreate() {
        multimap = ArrayListMultimap.create();
        // 相当于 Map.put("a", ArrayList(1, 1, 2, 3))
        multimap.put("a", 1);
        multimap.put("a", 1);
        multimap.put("a", 2);
        multimap.put("a", 3);
        multimap.put("b", 4);
        multimap.put("c", 5);

        LOGGER.info("MultimapCases init, multimap = {}", multimap);
    }

    /**
     * Multimap 的视图
     * 1. keys():
     *  1) 返回 Multiset 表示 Multimap 中的所有键，每个键重复出现的次数等于它映射的值的个数
     *  2) 返回结果支持 remove 操作，但不支持 add 操作
     *
     * 2. asMap():
     *  1) 返回 Map<K,Collection<V>> 形式的视图
     *  2) 返回结果支持 remove 操作，但不支持 put/putAll 操作
     *
     * 3. entries(): 返回 Collection<Map.Entry<K, V>>
     *
     * 4. values(): 返回 Collection<V> 包含所有 value
     *
     * 5. keySet(): 返回 Set 表示Multimap中所有不同的键
     */
    public void testViews() {
        assertEquals("[a x 4, b, c]", multimap.keys().toString());

        assertEquals("{a=[1, 1, 2, 3], b=[4], c=[5]}", multimap.asMap().toString());

        assertEquals("1 1 2 3", CollectionHelper.toString(multimap.get("a")));
        assertEquals("4", CollectionHelper.toString(multimap.get("b")));
        assertEquals("5", CollectionHelper.toString(multimap.get("c")));

        assertEquals("[1, 1, 2, 3, 4, 5]", multimap.values().toString());

        for (Map.Entry entry : multimap.entries()) {
            LOGGER.info("k = {}, v = {}", entry.getKey(), entry.getValue());
        }

        assertEquals("[a, b, c]", multimap.keySet().toString());
    }

}
