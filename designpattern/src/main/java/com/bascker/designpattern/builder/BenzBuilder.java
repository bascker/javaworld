package com.bascker.designpattern.builder;

import com.bascker.designpattern.builder.bean.Benz;
import com.bascker.designpattern.builder.bean.Car;

import java.util.List;

/**
 * Benz Builder
 *
 * @author bascker
 */
public class BenzBuilder extends CarBuilder {

    private List<String> mSequence;

    @Override
    public void setSequence(final List<String> sequence) {
        mSequence = sequence;
    }

    @Override
    public Car builder() {
        return new Benz(mSequence);
    }
}
