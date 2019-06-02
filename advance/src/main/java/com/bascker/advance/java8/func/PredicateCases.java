package com.bascker.advance.java8.func;

import org.testng.annotations.Test;

import java.util.function.BiPredicate;

import static junit.framework.TestCase.assertTrue;

/**
 * Predicate 系列接口案例
 */
@Test
public class PredicateCases {

    /**
     * IntPredicate: 接受一个 int 输入参数，返回一个布尔值的结果
     * LongPredicate: 接受一个 long 输入参数，返回一个布尔值类型结果。
     * DoublePredicate: 接受一个 double 输入参数，返回一个布尔值类型结果。
     * BiPredicate: 接受一个 2 个输入参数，返回一个布尔值类型结果。
     */
    public void testBiPredicate() {
        final BiPredicate<Integer, Integer> isMax = (x, y) -> x > y;
        assertTrue(isMax.test(2, 1));
    }

}