package com.bascker.base;

import com.bascker.bsutil.Sample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 方法重载:
 * Q1. 当使用 Object 类型充当参数时，进行重载会怎么样?
 * Q2. 传递 null 时，怎么调用?
 *
 * @author bascker
 */
public class MethodOverloadSample implements Sample {

    private static final Logger LOG = LoggerFactory.getLogger(MethodOverloadSample.class);

    public static void main(String[] args) {
        final MethodOverloadSample sample = new MethodOverloadSample();
        sample.start();
    }

    @Override
    public void start(final Object... args) {
        show("bascker");
        show(1);
        // 由于 null 不是对象，所以不会走 show(obj)
        // 若还有一个 show(Integer num), 则 show(null) 会编译错误，无法确定执行哪个方法
        show(null);
    }

    static void show(final Object obj) {
        LOG.info("show(obj): " + obj.toString());
    }

    static void show(final String str) {
        LOG.info("show(str): " + str);
    }

}
