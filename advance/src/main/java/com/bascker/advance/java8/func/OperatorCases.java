package com.bascker.advance.java8.func;

import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.function.BinaryOperator;
import java.util.function.IntUnaryOperator;
import java.util.function.UnaryOperator;

import static org.testng.Assert.assertEquals;

/**
 * Operator 系列接口案例
 *
 * @author bascker
 */
@Test
public class OperatorCases {

    /**
     * BinaryOperator: 接受 2 个 T 值操作符的操作，并返回了一个 T 类型结果。
     * DoubleBinaryOperator: 接受 2 个 double 值操作符的操作，并返回了一个 double 类型结果。
     * IntBinaryOperator: 接受 2 个 int 值操作符的操作，并返回了一个 int 类型结果。
     * LongBinaryOperator: 接受 2 个 long 值操作符的操作，并返回了一个 long 类型结果。
     */
    public void testBinaryOperator() {
        final BinaryOperator<Integer> add = (x, y) -> x + y;
        assertEquals(Integer.valueOf(7), add.apply(3, 4));

        final BinaryOperator<Integer> op = BinaryOperator.minBy(Comparator.naturalOrder());
        assertEquals(Integer.valueOf(2), op.apply(2, 3));
    }

    /**
     * UnaryOperator: 接受 1 个 T 类型的参数,返回 T 类型的结果。
     * DoubleUnaryOperator: 接受 1 个 double 类型的参数,返回 double 类型的结果。
     * IntUnaryOperator: 接受 1 个 int 类型的参数,返回 int 类型的结果。
     * LongUnaryOperator: 接受 1 个 long 类型的参数,返回 long 类型的结果。
     */
    public void testIntUnaryOperator() {
        final UnaryOperator<Integer> power3 = x -> Double.valueOf(Math.pow(x, 3)).intValue();
        assertEquals(Integer.valueOf(8), power3.apply(2));
    }

}