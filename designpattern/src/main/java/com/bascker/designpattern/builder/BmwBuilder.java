package com.bascker.designpattern.builder;

import com.bascker.designpattern.builder.bean.Bmw;
import com.bascker.designpattern.builder.bean.Car;

import java.util.List;

/**
 * BMW Builder
 *
 * @author bascker
 */
public class BmwBuilder extends CarBuilder {

    private List<String> mSequence;

    @Override
    public void setSequence(final List<String> sequence) {
        mSequence = sequence;
    }

    @Override
    public Car builder() {
        return new Bmw(mSequence);
    }
}
