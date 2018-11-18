package com.bascker.springframework.aop;

import com.bascker.springframework.service.Apology;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;
import org.springframework.stereotype.Component;

/**
 * 引入增强：对类的增强
 *
 * AOP 中，对方法的增强叫织入（Weaving），对类的增强叫引入（Introduction）
 *
 * @author bascker
 */
@Component
public class IntroAdvice extends DelegatingIntroductionInterceptor implements Apology {

    private static final Logger LOGGER = LoggerFactory.getLogger(IntroAdvice.class);

    @Override
    public void sorry(final String msg) {
        LOGGER.info("sorry, {}", msg);
    }

}
