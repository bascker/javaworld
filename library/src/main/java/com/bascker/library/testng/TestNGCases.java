package com.bascker.library.testng;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Calendar;

/**
 * TestNG 基础使用
 *
 * 1.@Test 注解
 *  1.1 可标注类/方法
 *      1) 标注类时, 所有 public 方法都会被视为测试方法
 *      2) 标注方法时, 即使是 private 方法，都会当作测试方法
 *  1.2
 *  1.3
 *
 * 2.@Parameters
 *  2.1 可用作较为简单的参数传递: 会在 testng.xml 中查找定义
 *  2.2 若在 testng.xml 中没有定义参数值, 则可使用 @Optional 来传参
 *
 * 3.@DataProvider
 *  3.1 数据提供者，用于提供测试数据，与 @Test 的 dataProvider, dataProviderClass 属性搭配使用
 *  3.2 被 @DataProvider 标注的方法，返回值有要求
 *      1) 返回 Object[][] 二维数组: 每个一维数组作为参数传递给测试方法，N 个一维数组，测试方法执行 N 遍
 *      2) 返回 Iterator<Object[]>: 用于延迟创建测试数据，测试方法逐一获取迭代器内的数据
 *  3.3 若想某个 DataProvider 可跨类调用，则加上 static 修饰，并在跨类调用时，指定 @Test 的 dataProviderClass 属性值
 *  3.4 @Test 的 dataProvider 值必须与 @DataProvider 的 name 一致
 *  3.5 若 DataProvider 方法的第一个参数是 reflect.Method，则会使用测试方法的名称作为参数传递
 *
 * 4.方法依赖
 *  4.1 两种依赖关系
 *      1) 硬依赖: 被依赖方法必须全部成功执行，才会执行测试方法, 否则输出标记"Test Ignored". 默认 alwaysRun = false
 *      2) 软依赖: 无论被依赖方法是否成功执行, 都会执行测试方法. 设置 alwaysRun = true
 *  4.2 @Test 的 dependsOnMethods & dependsOnGroups 来定义测试方法的依赖
 *
 * @author bascker
 */
@Test
public class TestNGCases {

    private static final Logger LOG = LoggerFactory.getLogger(TestNGCases.class);

    public void hello() {
        LOG.info("[TestNG] hello");
    }

    /**
     * private 权限的测试方法
     */
    @Test
    private void now() {
        LOG.info("[TestNG] " + Calendar.getInstance().getTime());
    }

    /**
     * Parameters + Optional 注解的使用
     * @param msg
     */
    @Parameters("msg")
    public void message(@Optional(value = "hello, TestNG") final String msg) {
        LOG.info("[TestNG] " + msg);
    }

    // --------------------------------------
    // 数据提供者
    // --------------------------------------

    /**
     * 定义一个 DataProvider
     * @return
     */
    @DataProvider
    private Object[][] createMsg() {
        return new Object[][]{{"Data From createMsg"}, {"This is second Object[]"}};
    }

    @Test(dataProvider = "createMsg")
    public void testCreateMSG(final String msg) {
        LOG.info("[TestNG] msg = " + msg);
    }

    @Test(dataProvider = "names", dataProviderClass = DataProviders.class)
    public void showName(final String name) {
        LOG.info("[TestNG] name = " + name);
    }

    // --------------------------------------
    // 依赖关系
    // --------------------------------------

    /**
     * 依赖 hello 方法, 在 hello 之后执行
     */
    @Test(dependsOnMethods = "hello")
    public void afterHello() {
        LOG.info("[TestNG] afterHello");
    }

    @Test(groups = {"ms"})
    public void m1() {
        LOG.info("[TestNG] m1");
    }

    @Test(groups = {"ms.2"})
    public void m2() {
        LOG.info("[TestNG] m2");
    }

    @Test(groups = {"ms.3"})
    public void m3() {
        LOG.info("[TestNG] m3");
    }

    /**
     * 匹配正则式 ms.*，在符合该表达式的群组方法执行后执行
     */
    @Test(dependsOnGroups = "ms.*", alwaysRun = true)
    public void afterMs() {
        LOG.info("[TestNG] afterMs");
    }

}
