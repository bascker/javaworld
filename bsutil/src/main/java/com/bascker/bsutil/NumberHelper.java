package com.bascker.bsutil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NumberHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(NumberHelper.class);

    public static String toHex(final Long number) {
        return Long.toHexString(number);
    }

}
