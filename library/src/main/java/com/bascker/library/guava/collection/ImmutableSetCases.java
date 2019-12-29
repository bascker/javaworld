package com.bascker.library.guava.collection;

import com.google.common.collect.ImmutableSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * 不可变集合 ImmutableSet 案例
 *
 * @author bascker
 */
@Test
public class ImmutableSetCases {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImmutableSetCases.class);

    private Set<String> names = new HashSet<>();

    @BeforeTest
    public void init() {
        LOGGER.info("ImmutableSetCases init start");

        names.add("bascker");
        names.add("lisa");
        names.add("paul");

        LOGGER.info("ImmutableSetCases init end");
    }

    /**
     * 创建不可变集合
     * 1. copyOf()
     * 2. of()
     * 3. <T>builder()
     */
    public void testCreate() {
        // copyOf
        ImmutableSet<String> iSetByCoptOf = ImmutableSet.copyOf(names);
        LOGGER.info("iSetByCoptOf = {}", iSetByCoptOf);

        // of()
        ImmutableSet<String> iSetByOf = ImmutableSet.of("a", "b", "c");
        LOGGER.info("iSetByOf = {}", iSetByOf);

        // builder()
        ImmutableSet<String> iSetByBuilder = ImmutableSet.<String>builder().addAll(names).add("johnnie").build();
        LOGGER.info("iSetByBuilder = {}", iSetByBuilder);
    }

}
