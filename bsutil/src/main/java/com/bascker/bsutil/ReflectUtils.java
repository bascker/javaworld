package com.bascker.bsutil;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 反射工具类
 *
 * @author bascker
 */
public class ReflectUtils {

    private static final Logger LOG = LoggerFactory.getLogger(ReflectUtils.class);

    public static Field getField (final Class clazz, final String fieldName) throws NoSuchFieldException {
        return clazz.getDeclaredField(fieldName);
    }

    public static Method getMethod (final Class clazz, final String methodName) throws NoSuchMethodException {
        final List<Method> methods = Arrays.stream(clazz.getDeclaredMethods())
                .filter(method -> methodName.equals(method.getName()))
                .collect(Collectors.toList());

        return methods.get(0);
    }

    /**
     * map 转换为指定类型的对象: 为了避免 mName 这种带固定前缀的属性名，从而走 setter 方法
     * TODO: 性能问题, {@link ReflectUtils#getMethod(Class, String)} 每次找一个方法都是走一遍循环，性能较低
     * @param map
     * @param valueType
     * @param <T>
     * @return
     */
    public static <T> T map2Object (final Map<String, Object> map, final Class<T> valueType) throws IllegalAccessException, InstantiationException {
        final T obj = valueType.newInstance();
        final String METHOD_PREFIX = "set";

        map.forEach((fieldName, fieldValue) -> {
            final StringBuilder sb = new StringBuilder();
            try {
                final Method method = ReflectUtils.getMethod(valueType,
                        sb.append(METHOD_PREFIX).append(StringUtils.capitalize(fieldName)).toString());
                method.invoke(obj, fieldValue);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                LOG.error(e.getMessage());
            }
        });

        return obj;
    }

    private ReflectUtils () {}

}
