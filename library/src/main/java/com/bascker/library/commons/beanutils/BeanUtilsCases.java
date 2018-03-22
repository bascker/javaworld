package com.bascker.library.commons.beanutils;

import com.bascker.bsutil.bean.Address;
import com.bascker.bsutil.bean.Person;
import com.bascker.bsutil.bean.Sex;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * BeanUtils Case
 *
 * 1.BeanUtils
 *  1.1 getProperty():       获取属性值
 *  1.2  getArrayProperty(): 获取数组/集合字段值, 以数组形式，返回对应值
 *  1.3 getNestedProperty(): 获取嵌套属性值
 *  1.4 setProperty():       设置属性值
 *  1.5 cloneBean():         深度复制
 *  1.6 describe():          Bean 转 Map
 *  1.7 populate():          批量替换值
 *
 * @author bascker
 */
public class BeanUtilsCases {

    private static final Logger LOG = LoggerFactory.getLogger(BeanUtilsCases.class);
    private Person mPerson;

    @Before
    public void before () {
        mPerson = new Person("bascker", 24, Sex.MALE);
        mPerson.setHabits(Arrays.asList("guitar", "basketball", "beautiful girl", "dog"));
        mPerson.setLuckyNums(new int[]{1, 2, 3, 6, 8});
        final Address address = new Address("China", "ZheJiang", "HangZhou");
        mPerson.setAddress(address);
    }

    @Test
    public void testGetProperty () throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Assert.assertEquals("bascker", BeanUtils.getProperty(mPerson, "name"));
        Assert.assertEquals(24, Integer.parseInt(BeanUtils.getProperty(mPerson, "age")));
    }

    @Test
    public void testSetProperty () throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        BeanUtils.setProperty(mPerson, "name", "paul");
        Assert.assertEquals("paul", BeanUtils.getProperty(mPerson, "name"));

        // BeanUtils 对 URL 的处理
        BeanUtils.setProperty(mPerson, "github", "https://github.com/bascker/javaworld");
        final URL github = mPerson.getGithub();
        LOG.info("{ protocol: " + github.getProtocol()
                + ", host: " + github.getHost()
                + ", path: " + github.getPath()
                + " }");
    }

    @Test
    public void testCloneBean () throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        final Person paul = (Person) BeanUtils.cloneBean(mPerson);
        paul.setName("paul");
        paul.setAge(22);

        LOG.info(paul.toString());
        LOG.info(mPerson.toString());
    }

    @Test
    public void testDescribe () throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        final Map<String, String> map = BeanUtils.describe(mPerson);
        LOG.info(map.toString());
    }

    @Test
    public void testPopulate () throws InvocationTargetException, IllegalAccessException {
        final Map<String, Object> vals = new HashMap<>();
        vals.put("name", "an.");
        vals.put("age", 21);
        vals.put("sex", Sex.FEMALE);
        BeanUtils.populate(mPerson, vals);
        LOG.info(mPerson.toString());
    }

    @Test
    public void testGetArrayProperty () throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        // 数组类型属性值
        final String[] luckyNums = BeanUtils.getArrayProperty(mPerson, "luckyNums");
        Assert.assertArrayEquals(new String[]{"1", "2", "3", "6", "8"}, luckyNums);

        // 集合类型属性值
        final String[] habits = BeanUtils.getArrayProperty(mPerson, "habits");
        Assert.assertArrayEquals(new String[]{"guitar", "basketball", "beautiful girl", "dog"}, habits);
    }

    @Test
    public void testGetNestedProperty () throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Assert.assertEquals("ZheJiang", BeanUtils.getNestedProperty(mPerson, "address.province"));
    }

}
