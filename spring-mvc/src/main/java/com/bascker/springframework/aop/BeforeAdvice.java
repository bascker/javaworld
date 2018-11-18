package com.bascker.springframework.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * 前置增强类
 *
 * @author bascker
 */
public class BeforeAdvice implements MethodBeforeAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(BeforeAdvice.class);

    @Override
    public void before(final Method method, final Object[] objects, final Object o) throws Throwable {
        LOGGER.info("Before");
    }

}
