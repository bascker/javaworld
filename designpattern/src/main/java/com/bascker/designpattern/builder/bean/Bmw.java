package com.bascker.designpattern.builder.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 宝马
 *
 * @author bascker
 */
public class Bmw extends Car {

    private static final Logger LOG = LoggerFactory.getLogger(Bmw.class);

    public Bmw(final List<String> sequence) {
        super(sequence);
    }

    @Override
    protected void start() {
        LOG.info("BMW.start");
    }

    @Override
    protected void stop() {
        LOG.info("BMW.stop");
    }

    @Override
    protected void alarm() {
        LOG.info("BMW.alarm");
    }

    @Override
    protected void engineBoom() {
        LOG.info("BMW.engineBoom");
    }
}
