package com.bascker.advance.java8;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;

/**
 * Lambda 表达式
 *
 * @author bascker
 */
public class LambdaSample {

    private static final Logger LOG = LoggerFactory.getLogger(LambdaSample.class);

    /**
     * lambda 表达式的形式
     */
    @Test
    public void base (){
        // 1. 无参
        final Runnable noArgs = () -> LOG.info("Hello World!");
        new Thread(noArgs).start();

        // 2. 一个参数
        final ActionListener oneArg = event -> LOG.info("Clicked!");

        // 3. 包含代码块
        final Runnable multiStatement = () -> {
            LOG.info("Hello");
            LOG.info("World");
        };

        // 4. 多个参数, 注意这里是定义了一个函数
        final BinaryOperator<Long> add = (x, y) -> x + y;

        // 5. 显示声明参数类型
        final BinaryOperator<Long> addExplicit = (Long x, Long y) -> x + y;
    }

    /**
     * 类型推断
     */
    @Test
    public void testTypeInfer(){
        final Predicate<Integer> atLeast5 = x -> x > 5;
        LOG.info(atLeast5.toString());
    }

    /**
     * DateFormatter 类是非线程安全的，使用构造函数创建一个线程安全的 DateFormatter 对象，并输出日期
     */
    @Test
    public void dataformat () {
        final ThreadLocal<SimpleDateFormat> formatter = ThreadLocal.withInitial(() -> new SimpleDateFormat());
        final Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 1970);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        LOG.info(formatter.get().format(calendar.getTime()));
    }

}
