package com.bascker.springframework.service.impl;

import com.bascker.springframework.service.Apology;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Apology 实现类
 *
 * @author bascker
 */
@Service
public class ApologyImpl implements Apology {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApologyImpl.class);

    @Override
    public void sorry(final String msg) {
        LOGGER.info("sorry, {}", msg);
    }

    public void byPaul(final String msg) {
        LOGGER.info("paul: i am very sorry, {}", msg);
    }

    public void byLisa(final String msg) {
        LOGGER.info("lisa: i am very sorry too, {}", msg);
    }

}
