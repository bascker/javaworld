package com.bascker.designpattern.builder;

import com.bascker.designpattern.builder.bean.Car;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Car Builder Unit Test
 *
 * @author bascker
 */
public class CarBuilderTest {

    @Test
    public void test () {
        final List<String> sequnce = Arrays.asList("engineBoom", "start", "stop");
        final CarBuilder bmwBuilder = new BmwBuilder();
        bmwBuilder.setSequence(sequnce);
        final Car bmw = bmwBuilder.builder();
        bmw.run();
    }

}
