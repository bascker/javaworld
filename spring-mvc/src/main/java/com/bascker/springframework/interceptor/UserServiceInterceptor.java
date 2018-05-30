package com.bascker.springframework.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * UserService Interceptor
 *
 * @author bascker
 */

@Service
public class UserServiceInterceptor implements Interceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceInterceptor.class);

    @Override
    public void before() {
        LOGGER.info("before");
    }

    @Override
    public void after() {
        LOGGER.info("after");
    }
}
