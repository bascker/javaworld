package com.bascker.library.guava.collection;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * BiMap 案例
 * 1. 保证值是唯一的，因此 values() 返回 Set 而不是普通的 Collection
 * 2. inverse(): 反转 BiMap<K, V> 的键值映射
 *
 * @author bascker
 */

@Test
public class BiMapCases {

    private static final Logger LOGGER = LoggerFactory.getLogger(BiMapCases.class);

    private BiMap<String, Object> biMap;

    @BeforeTest
    public void testCreate() {
        biMap = HashBiMap.create();
        biMap.put("name", "bascker");
        biMap.put("age", 24);
        LOGGER.info(biMap.toString());

        final BiMap<Object, String> inverseBiMap = biMap.inverse();
        LOGGER.info(inverseBiMap.toString());
    }

}
