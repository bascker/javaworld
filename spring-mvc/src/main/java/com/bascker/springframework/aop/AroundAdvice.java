package com.bascker.springframework.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 环绕通知
 *
 * @author bascker
 */
@Component
public class AroundAdvice implements MethodInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(AroundAdvice.class);

    @Override
    public Object invoke(final MethodInvocation invocation) throws Throwable {
        before();
        final Object rs = invocation.proceed();
        after();
        return rs;
    }

    private void before() {
        LOGGER.info("Before");
    }

    private void after() {
        LOGGER.info("After");
    }

}
