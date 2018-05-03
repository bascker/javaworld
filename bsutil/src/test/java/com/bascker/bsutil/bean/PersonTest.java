package com.bascker.bsutil.bean;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Person Unit Test
 * @author bascker
 */
public class PersonTest {

    @Test
    public void testBuilder() {
        final Person bascker = new Person.Builder("bascker", 24, Sex.MALE)
                .habits(Arrays.asList("吉他", "篮球", "MM")).build();
        Assert.assertEquals("bascker", bascker.getName());
        Assert.assertEquals(24, bascker.getAge());
        Assert.assertEquals(Sex.MALE, bascker.getSex());
        Assert.assertEquals(Arrays.asList("吉他", "篮球", "MM"), bascker.getHabits());
    }

}
