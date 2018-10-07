package com.bascker.bsutil;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * 类型转换工具类
 *
 * @author bascker
 */
public class CastUtils {

    /**
     * 转为 String 类型，默认值为空串
     * @param object
     * @return
     */
    public static String castString(final Object object) {
        return castString(object, Constant.EMPTY_STR);
    }

    /**
     * 转为 String 类型
     * @param object
     * @param defaultValue
     * @return
     */
    public static String castString(final Object object, final String defaultValue) {
        return Objects.nonNull(object) ? String.valueOf(object) : defaultValue;
    }

    /**
     * 转为 Double 类型，默认值是 0D
     * @param object
     * @return
     */
    public static double castDouble(final Object object) {
        return castDouble(object, Constant.ZERO);
    }

    /**
     * 转为 Double 类型
     * @param object
     * @param defaultValue
     * @return
     */
    public static double castDouble(final Object object, final double defaultValue) {
        final String strValue = castString(object);
        return StringUtils.isNotEmpty(strValue) ? Double.valueOf(strValue) : defaultValue;
    }

    /**
     * 转为 Long 类型，默认值为 0L
     * @param object
     * @return
     */
    public static long castLong(final Object object) {
        return castLong(object, Constant.ZERO);
    }

    /**
     * 转为 Long 类型
     * @param object
     * @param defaultValue
     * @return
     */
    public static long castLong(final Object object, final long defaultValue) {
        final String strValue = castString(object);
        return StringUtils.isNotEmpty(strValue) ? Long.valueOf(strValue) : defaultValue;
    }

    /**
     * 转为 int 类型，默认值为 0
     * @param object
     * @return
     */
    public static int castInt(final Object object) {
        return castInt(object, Constant.ZERO);
    }

    /**
     * 转为 int 类型
     * @param object
     * @return
     */
    public static int castInt(final Object object, final int defaultValue) {
        final String strValue = castString(object);
        return StringUtils.isNotEmpty(strValue) ? Integer.valueOf(strValue) : defaultValue;
    }

    /**
     * 转为 Boolean 类型，默认值为 false
     * @param object
     * @return
     */
    public static boolean castBoolean(final Object object) {
        return castBoolean(object, Boolean.FALSE);
    }

    /**
     * 转为 Boolean 类型
     * @param object
     * @param defaultValue
     * @return
     */
    public static boolean castBoolean(final Object object, final boolean defaultValue) {
        final String strValue = castString(object);
        return StringUtils.isNotEmpty(strValue) ? Boolean.valueOf(strValue) : defaultValue;
    }

    private CastUtils() {}

}
