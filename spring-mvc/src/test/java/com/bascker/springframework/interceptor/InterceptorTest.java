package com.bascker.springframework.interceptor;

import com.bascker.springframework.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Interceptor Unit Test
 *
 * @author bascker
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
@WebAppConfiguration
public class InterceptorTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private DynamicProxyHandler handler;

    @Autowired
    private UserService userService;

    @Test
    public void test() {
        final UserService proxy = (UserService) handler.bind(userService);
        System.out.println(proxy.get("1"));
    }

}
