package com.bascker.library.guava.base;

import com.google.common.base.Preconditions;
import org.testng.annotations.Test;

import java.util.function.BiFunction;
import java.util.function.Function;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

/**
 * Preconditions 使用案例
 *
 * @author bascker
 */
@Test
public class PreconditionsCases {

    /**
     * checkArgument(boolean b, @NullableDecl String errorMessageTemplate, char p1) 参数说明：
     * 1. b：判断表达式
     * 2. errorMessageTemplate：当 b 为 false 时，提示的信息
     * 3. p1：参数的值，可多个
     *
     * 若校验失败，则返回 IllegalArgumentException
     */
    public void testCheckArgument() {
        final Function<Integer, Integer> fun = x -> {
            Preconditions.checkArgument(x != 0, "input params x cannot be zero!", x);
            return x * 2;
        };

        assertEquals(Integer.valueOf(4), fun.apply(2));
        assertThrows(IllegalArgumentException.class, () -> fun.apply(0));
        try {
            fun.apply(0);
        } catch (IllegalArgumentException e) {
            assertEquals("input params x cannot be zero! [0]", e.getLocalizedMessage());
        }
    }

    /**
     * checkNotNull(T t): 检查入参是否为 null，若为 null，则抛出 NPE
     */
    public void testCheckNotNull() {
        final Function<String, Integer> str2Int = str -> {
            String param = Preconditions.checkNotNull(str, "The input param str is null");
            return Integer.valueOf(param);
        };

        assertEquals(new Integer(2), str2Int.apply("2"));
        assertThrows(NullPointerException.class, () -> str2Int.apply(null));
        try {
            str2Int.apply(null);
        } catch (NullPointerException e) {
            assertEquals("The input param str is null", e.getLocalizedMessage());
        }
    }

    /**
     * checkState(boolean state): 检查传入的状态是否为 true，若不是，则抛出 IllegalStateException
     */
    public void testCheckState() {
        final boolean isActive = false;
        assertThrows(IllegalStateException.class, () -> Preconditions.checkState(isActive));
    }

    /**
     * checkPositionIndex(int index, int size): 检查索引值 index 是否在列表/字符串/数组合法范围内 [0, size]，
     * 若不是，则抛出 IndexOutOfBoundsException
     */
    public void testCheckPositionIndex() {
        final BiFunction<String, Integer, Character> getChar = (str, index) -> {
            Preconditions.checkNotNull(str, "The param str is invalid");
            Preconditions.checkPositionIndex(index, str.length(), "The param index is invalid");
            return str.charAt(index);
        };

        assertEquals(Character.valueOf('c'), getChar.apply("abc", 2));
        assertThrows(IndexOutOfBoundsException.class, () -> getChar.apply("abc", 5));
    }

}