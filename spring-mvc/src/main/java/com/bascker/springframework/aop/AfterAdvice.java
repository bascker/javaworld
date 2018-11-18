package com.bascker.springframework.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * 后置增强类
 *
 * @author bascker
 */
public class AfterAdvice implements AfterReturningAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(AfterAdvice.class);

    @Override
    public void afterReturning(final Object o, final Method method, final Object[] objects, final Object o1) throws Throwable {
        LOGGER.info("After");
    }

}
