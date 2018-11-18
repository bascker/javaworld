package com.bascker.springframework;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;

/**
 * 测试基类, 所有 UT 继承该类
 *
 * @author bascker
 */
@Test
@WebAppConfiguration
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class BaseContextTests extends AbstractTestNGSpringContextTests {

}
