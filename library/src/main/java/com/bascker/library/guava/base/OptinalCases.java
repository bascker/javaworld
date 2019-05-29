package com.bascker.library.guava.base;

import com.bascker.bsutil.bean.Person;
import com.google.common.base.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Optional 使用案例
 *
 * Optional 优点：
 * 1. 最大好处：使用 Optional 会强制开发去思考对象取值（入参或返回值）为 null 的情况
 * 2. 增加可读性
 *
 * @author bascker
 */
@Test
public class OptinalCases {

    private static final Logger LOGGER = LoggerFactory.getLogger(OptinalCases.class);

    /**
     * 测试创建 Optional 对象的方式
     */
    public void testCreateOptional() {
        /*
         * absent(): 返回一个没包含任何引用的 Optional 实例.
         */
        final Optional<Integer> var1 = Optional.absent();
        // Optional 的 toString() 会打印构造 Optional 对象时的方式
        assertEquals("Optional.absent()", var1.toString());
        // 对没有包含引用的 Optional 对象调用 Optional.get() 会抛出 IllegalStateException
        assertThrows(IllegalStateException.class, () -> var1.get());

        /*
         * of(T t): t 不能为空，否则在调用是抛出 NPE
         */
        final Optional var2 = Optional.of(new Integer(0));
        assertEquals(new Integer(0), var2.get());
        assertEquals("Optional.of(0)", var2.toString());
        assertThrows(NullPointerException.class, () -> Optional.of(null));

        final Optional<Person> var3 = Optional.of(new Person("bascker"));
        assertEquals("bascker", var3.get().getName());

        /*
         * fromNullable(): 允许传入 null，但若传入 null，则调用的是 Optional.absent() 进行 Optional 实例的构造
         */
        final Optional<Integer> var4 = Optional.fromNullable(null);
        assertEquals("Optional.absent()", var4.toString());
    }

    /**
     * isPresent()
     * 1. 实例方法
     * 2. 若 Optional 对象包含的引用非空，则返回 true
     */
    public void testIsPresent() {
        final Optional var1 = Optional.of(0);
        assertTrue(var1.isPresent());

        final Optional var2 = Optional.absent();
        assertFalse(var2.isPresent());
    }

    /**
     * or(T t): 若 Optional 对象包含的引用不存在，则返回指定值, 类似 {@link java.util.Map#getOrDefault(Object, Object)}
     */
    public void testOr() {
        final Optional<Integer> var = Optional.absent();
        assertEquals(Integer.valueOf(2), var.or(2));
    }

    /**
     * orNull(): 若 Optional 对象包含的引用不存在，则返回 null
     */
    public void testOrNull() {
        final Optional var = Optional.absent();
        assertNull(var.orNull());
    }

}
