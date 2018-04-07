package com.bascker.designpattern.builder.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Benz
 *
 * @author bascker
 */
public class Benz extends Car {

    private static final Logger LOG = LoggerFactory.getLogger(Benz.class);

    public Benz(final List<String> sequence) {
        super(sequence);
    }

    @Override
    protected void start() {
        LOG.info("Benz.start");
    }

    @Override
    protected void stop() {
        LOG.info("Benz.stop");
    }

    @Override
    protected void alarm() {
        LOG.info("Benz.alarm");
    }

    @Override
    protected void engineBoom() {
        LOG.info("Benz.engineBoom");
    }
}
