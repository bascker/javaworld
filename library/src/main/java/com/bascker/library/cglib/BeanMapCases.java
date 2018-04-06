package com.bascker.library.cglib;

import com.bascker.bsutil.bean.Person;
import com.bascker.bsutil.bean.Sex;
import net.sf.cglib.beans.BeanMap;
import org.junit.Assert;
import org.junit.Test;

/**
 * BeanMap Cases
 *
 * 1.BeanMap
 *  1.1 将一个 bean 对象中的所有属性转换为一个 Map
 *
 * @author bascker
 */
public class BeanMapCases {

    @Test
    public void base () {
        final Person person = new Person("bascker", 24, Sex.MALE);
        final BeanMap beanMap = BeanMap.create(person);
        Assert.assertEquals("bascker", beanMap.get("name"));
        Assert.assertEquals(24, beanMap.get("age"));
        Assert.assertEquals(Sex.MALE, beanMap.get("sex"));
    }

}
