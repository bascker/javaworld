package com.bascker.springframework.aop;

import com.bascker.bsutil.bean.Person;
import com.bascker.springframework.BaseContextTests;
import com.bascker.springframework.service.Apology;
import com.bascker.springframework.service.UserService;
import com.bascker.springframework.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.ProxyFactory;

/**
 * Spring AOP 增强测试
 *
 * @author bascker
 */
public class AdviceTest extends BaseContextTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdviceTest.class);

    public void test() {
        // 创建 Spring 代理工厂类
        final ProxyFactory proxyFactory = new ProxyFactory();
        // 注入目标代理对象
        proxyFactory.setTarget(new Person("bascker"));
        proxyFactory.addAdvice(new BeforeAdvice());
        proxyFactory.addAdvice(new AfterAdvice());
        // 获取代理对象
        final Person proxy = (Person) proxyFactory.getProxy();
        // 调用方法
        LOGGER.info(proxy.toString());
    }

    public void testAroundAdvice() {
        final UserService userService = (UserService) applicationContext.getBean("userProxy");
        LOGGER.info(userService.get("1").toString());
    }

    public void testIntroAdvice() {
        // 转换类型为目标类 UserService，而非 UserService 接口
        final UserServiceImpl userService = (UserServiceImpl) applicationContext.getBean("userProxy2");
        LOGGER.info(userService.get("1").toString());

        // 能将非 Apology 的实现类 UserServiceImpl 向上强转为 Apology 接口，
        // 是引入增强带来的特性
        final Apology apology = (Apology) userService;
        apology.sorry("test for Introduction Advice");
    }

}