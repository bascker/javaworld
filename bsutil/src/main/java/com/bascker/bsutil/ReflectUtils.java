package com.bascker.bsutil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射工具类
 *
 * @author bascker
 */
public class ReflectUtils {

    public static Field getField (final Class clazz, final String fieldName) throws NoSuchFieldException {
        final Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);

        return field;
    }

    public static Method getMethod (final Class clazz, final String methodName) throws NoSuchMethodException {
        final Method method = clazz.getDeclaredMethod(methodName);
        method.setAccessible(true);

        return method;
    }

    private ReflectUtils () {}

}
