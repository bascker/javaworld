package com.bascker.library.testng;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.util.Calendar;

/**
 * TestNG Life Cycle
 *
 * 1.TestNG 生命周期:
 *  1.1 beforeClass:  在类实例化后, 所有方法执行前运行
 *  1.1 beforeMethod: 在每个方法执行前运行
 *  1.1 afterMethod:  在每个方法执行后运行
 *  1.1 afterClass:   在所有方法执行后运行
 *
 * @author bascker
 */
@Test
public class TestNGLifeCycleCases {

    private static final Logger LOG = LoggerFactory.getLogger(TestNGLifeCycleCases.class);

    @BeforeClass
    public void beforeClass() {
        LOG.info("[TestNG] beforeClass");
    }

    @BeforeMethod
    public void beforeMethod() {
        LOG.info("[TestNG] beforeMethod");
    }

    public void hello() {
        LOG.info("[TestNG] hello, testng");
    }

    public void now() {
        LOG.info("[TestNG] " + Calendar.getInstance().getTime());
    }

    @AfterMethod
    public void afterMethod() {
        LOG.info("[TestNG] afterMethod");
    }

    @AfterClass
    public void afterClass() {
        LOG.info("[TestNG] afterClass");
    }

}
