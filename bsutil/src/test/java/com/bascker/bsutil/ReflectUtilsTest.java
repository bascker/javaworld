package com.bascker.bsutil;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * ReflectUtils Unit Test
 *
 * @author bascker
 */
public class ReflectUtilsTest {

    @Test
    public void testGetMethod () throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        final Method method = ReflectUtils.getMethod(ReflectUtilsTest.class, "hello");
        final ReflectUtilsTest test = new ReflectUtilsTest();
        method.invoke(test);
    }

    private void hello () {
        System.out.println("I'm bascker");
    }
}
