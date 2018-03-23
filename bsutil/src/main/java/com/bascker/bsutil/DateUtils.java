package com.bascker.bsutil;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 时间日期处理工具
 *
 * @author bascker
 */
public class DateUtils {

    private static final String DEFAULT_PATTERN = "yyyy/MM/dd HH:mm:ss";

    /**
     * 获取当前时间
     * @param pattern   日期格式化串
     * @return
     */
    public static String now (final String pattern) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        final Calendar calendar = Calendar.getInstance();
        return dateFormat.format(calendar.getTime());
    }

    public static String now () {
        return now(DEFAULT_PATTERN);
    }


    private DateUtils () {}

}
