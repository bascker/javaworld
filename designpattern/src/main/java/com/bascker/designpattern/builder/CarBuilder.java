package com.bascker.designpattern.builder;

import com.bascker.designpattern.builder.bean.Car;

import java.util.List;

/**
 * Car Builder
 *
 * @author bascker
 */
public abstract class CarBuilder {

    public abstract void setSequence(final List<String> sequence);

    public abstract Car builder ();

}
