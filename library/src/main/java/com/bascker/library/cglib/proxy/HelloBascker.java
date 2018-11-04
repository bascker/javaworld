package com.bascker.library.cglib.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloBascker implements Hello {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloBascker.class);

    @Override
    public void say() {
        LOGGER.info("Hello Bascker");
    }

}
