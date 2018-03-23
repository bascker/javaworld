package com.bascker.bsutil;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DateUtils Unit Test
 *
 * @author bascker
 */
public class DateUtilsTest {

    private static final Logger LOG = LoggerFactory.getLogger(DateUtilsTest.class);

    @Test
    public void testNow () {
        LOG.info(DateUtils.now());
    }

}
