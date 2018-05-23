package com.bascker.library.powermock;

import org.apache.commons.lang3.StringUtils;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.testng.PowerMockObjectFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.IObjectFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.ObjectFactory;
import org.testng.annotations.Test;

import static org.mockito.Matchers.anyString;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * PowerMockito & TestNG 来 Mock static 方法
 *
 * 1.PowerMockito & TestNG Mock static 方法的步骤
 *  1) @PrepareForTest: 指定要 Mock 的静态类
 *  2) mockStatic(): 指定要 Mock 的静态类
 *  3) @ObjectFactory: 重点
 *  4) mock 对象方法调用
 *
 * 2.@PrepareForTest
 *  2.1 使用 PowerMockito 强大功能(如 Mock static/final/private 等方法)时，必须使用
 *  2.2 PowerMock 会根据开发者的 mock 要求，修改 @PrepareForTest 中指定的 class 文件, 以满足需求.
 *      若 mock 的是系统类的 final/static 方法，则不会直接修改系统类的 class 文件，而是修改调用系统类的 class s文件
 *  2.3 被 @PrepareForTest 标注后, 运行测试用例时, 会创建一个新的 {@link org.powermock.core.classloader.MockClassLoader} 实例,
 *      然后加载该测试用例使用到的类(系统类除外)
 *
 * 3.IObjectFactory
 *  3.1 用于实例化被测试对象
 *  3.2 使用 @ObjectFactory 标注一个返回 IObjectFactory 的方法，表示使用该对象工厂来产生 TestNG 对象
 *
 * @author bascker
 */

@Test
@PrepareForTest(StringUtils.class)
public class PowerMockitoTestNGMockStaticCases {

    private static final Logger LOGGER = LoggerFactory.getLogger(PowerMockitoTestNGMockStaticCases.class);

    @BeforeClass
    public void beforeClass() {
        mockStatic(StringUtils.class);
    }

    @ObjectFactory
    public IObjectFactory setObjectFactory() {
        return new PowerMockObjectFactory();
    }

    public void testIsEmpty() {
        // mock isEmpty() 调用
        when(StringUtils.isEmpty(anyString())).thenReturn(true);
        final String str = "PowerMockito & TestNG";
        Assert.assertTrue(StringUtils.isEmpty(str));
    }

}
