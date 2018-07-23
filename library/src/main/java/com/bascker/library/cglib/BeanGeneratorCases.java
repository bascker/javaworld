package com.bascker.library.cglib;

import com.bascker.bsutil.CollectionHelper;
import com.bascker.bsutil.bean.Sex;
import net.sf.cglib.beans.BeanGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * BeanGenerator Cases
 *
 * 1.BeanGenerator
 *  1.1 能在运行时创建一个 Bean
 *
 * @author bascker
 */
public class BeanGeneratorCases {

    private static final Logger LOG = LoggerFactory.getLogger(BeanGeneratorCases.class);
    private BeanGenerator mBeanGenerator;

    @Before
    public void before () {
        mBeanGenerator = new BeanGenerator();
    }

    /**
     * 利用 BeanGenerator 创建一个与 {@link com.bascker.bsutil.bean.Person} 类似的 JavaBean
     */
    @Test
    public void base () throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // 属性
        mBeanGenerator.addProperty("name", String.class);
        mBeanGenerator.addProperty("age", Integer.class);
        mBeanGenerator.addProperty("sex", Sex.class);

        // 创建 Bean
        final Object person = mBeanGenerator.create();
        LOG.info(person.getClass().toString());

        // 虽然添加了, 但使用反射查看 Field 是没有这些值的
        LOG.info(CollectionHelper.toString(
                Arrays.stream(person.getClass().getFields())
                        .map(Field::getName)
                        .collect(Collectors.toSet())));

        // 获取 Getter & Setter
        final Method setName = person.getClass().getMethod("setName", String.class);
        setName.invoke(person, "bascker");

        final Method getName = person.getClass().getMethod("getName");
        Assert.assertEquals("bascker", getName.invoke(person));
    }

}
