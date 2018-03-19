package com.bascker.advance.java8;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * Optional Sample
 *
 * 1.Optional
 *  1.1 相当于值的容器，可通过 get 方法提取值
 *  1.2 用于替换 null 值
 *
 * @author bascker
 */
public class OptionalSample {

    private static final Logger LOG = LoggerFactory.getLogger(OptionalSample.class);

    @Test
    public void base () {
        // 创建某个值的 Optional 对象
        // isPresent(): 判断一个 Optional 对象是否有值
        final Optional<String> optional = Optional.of("a");
        Assert.assertEquals(true, "a".equals(optional.get()));
        Assert.assertTrue(optional.isPresent());

        // 创建一个空的 Optional 对象，并检查其是否有值
        Optional opEmpty = Optional.empty();
        Assert.assertFalse(opEmpty.isPresent());
        opEmpty = Optional.ofNullable(null);
        Assert.assertFalse(opEmpty.isPresent());

    }

}
