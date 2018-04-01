package com.bascker.designpattern.factory.factorymethod;

import com.bascker.designpattern.factory.factorymethod.sample.singleton.Emperor;
import com.bascker.designpattern.factory.factorymethod.sample.singleton.EmperorFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 * EmperorFactory Unit Test
 *
 * @author bascker
 */
public class EmperorFactoryTest {

    @Test
    public void test () {
        final Emperor emperor = EmperorFactory.getInstance();
        emperor.setName("嘉靖");
        Assert.assertEquals("嘉靖", emperor.getName());
    }

}
