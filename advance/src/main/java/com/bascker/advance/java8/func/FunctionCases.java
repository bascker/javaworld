package com.bascker.advance.java8.func;

import org.testng.annotations.Test;

import java.util.function.*;

import static org.testng.Assert.assertEquals;

/**
 * Function 系列接口案例
 *
 * @author bascker
 */
@Test
public class FunctionCases {

    /**
     * Function 接口：传入一个参数 T, 返回最终结果 R
     * @see java.util.function.Function
     */
    public void testFunction() {
        final Function<String, String> say = name -> "My name is " + name;
        assertEquals("My name is bascker", say.apply("bascker"));
    }

    /**
     * BiFunction 接口: 支持传入 2 个参数，返回最终结果
     * @see java.util.function.BiFunction
     */
    public void testBiFunction() {
        final BiFunction<Double, Double, Double> power = (x, y) -> Math.pow(x, y);
        assertEquals(4D, power.apply(2D, 2D));
    }

    /**
     * DoubleFunction: 接受一个 double 类型参数，返回指定类型 R 的结果
     * IntFunction: 接受一个 int 类型参数，返回指定类型 R 的结果
     * LongFunction: 接受一个 long 类型参数，返回指定类型 R 的结果
     * @see DoubleFunction
     */
    public void testDoubleFunction() {
        final DoubleFunction<String> double2Str = x -> String.valueOf(x);
        assertEquals("2.0", double2Str.apply(2d));
    }

    /**
     * DoubleToIntFunction: 接受一个 double 参数，返回 int 类型结果
     * DoubleToLongFunction: 接受一个 double 参数，返回 Long 类型结果
     * IntToDoubleFunction & IntToLongFunction & LongToIntFunction & LongToDoubleFunction 同理
     * @see
     */
    public void testDoubleToIntFunction() {
        final DoubleToIntFunction double2Int = d -> Double.valueOf(d).intValue();
        assertEquals("2", String.valueOf(double2Int.applyAsInt(2d)));
    }

    /**
     * ToIntBiFunction: 接受 2 个参数，返回 int 类型结果
     * ToIntFunction: 接受 1 个参数，返回 int 类型结果
     * ToDoubleBiFunction & ToDoubleFunction & ToLongBiFunction & ToLongFunction 同理
     */
    public void testToIntBiFunction() {
        final ToIntBiFunction<String, String> mul = (x, y) -> Integer.valueOf(x) * Integer.valueOf(y);
        assertEquals(2, mul.applyAsInt("1", "1"));
    }

}