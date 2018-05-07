package com.bascker.designpattern.singleton;

import org.junit.Test;

/**
 * EnumSingleton Unit Test
 *
 * @author bascker
 */
public class EnumSingletonTest {

    @Test
    public void test () {
        EnumSingleton.INSTANCE.say();
    }

}
