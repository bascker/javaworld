package com.bascker.springframework.interceptor;

import com.bascker.bsutil.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;

/**
 * Time Interceptor
 *
 * @author bascker
 */
public class TimeInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimeInterceptor.class);
    private static final Calendar calendar = Calendar.getInstance();

    public void now() {
        LOGGER.info("now: " + DateUtils.now("yyyy/MM/dd HH:mm:ss:S"));
    }

}
