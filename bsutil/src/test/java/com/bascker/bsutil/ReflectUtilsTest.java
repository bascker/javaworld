package com.bascker.bsutil;

import com.bascker.bsutil.bean.Person;
import com.bascker.bsutil.bean.Sex;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * ReflectUtils Unit Test
 *
 * @author bascker
 */
public class ReflectUtilsTest {

    private static final Logger LOG = LoggerFactory.getLogger(ReflectUtilsTest.class);

    @Test
    public void testGetMethod () throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        final Method method = ReflectUtils.getMethod(ReflectUtilsTest.class, "hello");
        final ReflectUtilsTest test = new ReflectUtilsTest();
        method.invoke(test);

        final Method setName = ReflectUtils.getMethod(Person.class, "setName");
        final Person person = new Person();
        setName.invoke(person, "bascker");
        LOG.info(person.toString());
    }

    @Test
    public void testMap2Object () throws InstantiationException, IllegalAccessException {
        final Map<String, Object> map = new HashMap<>();
        map.put("name", "bascker");
        map.put("age", 24);
        map.put("sex", Sex.MALE);

        Assert.assertEquals("Person{mName='bascker', mAge=24, mSex=MALE}", ReflectUtils.map2Object(map, Person.class).toString());
    }

    private void hello () {
        LOG.info("I'm bascker");
    }
}
