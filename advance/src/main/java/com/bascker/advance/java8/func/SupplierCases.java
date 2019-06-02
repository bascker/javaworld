package com.bascker.advance.java8.func;

import com.bascker.bsutil.bean.Person;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.function.Supplier;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * Supplier 系列接口案例
 *
 * @author bascker
 */
@Test
public class SupplierCases {

    /**
     * Supplier: 返回一个指定类型 T 的值
     * IntSupplier: 返回一个 int 值
     * LongSupplier: 返回一个 long 值
     * BooleanSupplier: 返回一个 Boolean 值
     * DoubleSupplier: 返回一个 double 值
     */
    public void testSupplier() {
        final Supplier<Person> personFactory = () -> Person.newInstance();
        assertEquals(Person.class, personFactory.get().getClass());
        assertNotNull(personFactory.get());
    }

}
