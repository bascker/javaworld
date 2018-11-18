package com.bascker.springframework.aop;

import com.bascker.springframework.BaseContextTests;
import com.bascker.springframework.service.impl.ApologyImpl;

/**
 * Advisor 切面测试
 *
 * @author bascker
 */
public class AdvisorTest extends BaseContextTests {

    public void test() {
        final ApologyImpl proxy = (ApologyImpl) applicationContext.getBean("userProxy3");
        // 不被代理的方法
        proxy.sorry("no advisor");
        // 被代理的方法
        proxy.byLisa("have advisor");
    }

}
