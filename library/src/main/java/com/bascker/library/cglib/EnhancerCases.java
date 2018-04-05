package com.bascker.library.cglib;

import net.sf.cglib.proxy.CallbackHelper;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.FixedValue;
import net.sf.cglib.proxy.NoOp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * Enhancer Sample
 *
 * 1.Enhancer
 *  1.1 既能够代理普通的class，也能够代理接口
 *  1.2 用于创建一个被代理对象的子类并且拦截所有的方法调用
 *      1) 包括从Object中继承的 toString 和 hashCode 方法
 *      2) 不能拦截 final 方法, 如 Object.getClass(), 无法对 final 类进行代理
 *  1.3 方法
 *      1) setSuperclass(): 设置父类型, 从 toString() 输出可知, CGLIB 生成的类是 setSuperClass() 传入类型的一个子类
 *      2) setCallback():
 *      3) create(): 用于创建增强对象的
 *
 * 2.FixedValue
 *  2.1 函数式接口, 用于对所有拦截的方法返回相同的值
 *
 * @author bascker
 */
public class EnhancerCases {

    private static final Logger LOG = LoggerFactory.getLogger(EnhancerCases.class);
    private Enhancer mEnhancer;

    @Before
    public void before () {
        mEnhancer = new Enhancer();
        mEnhancer.setSuperclass(HelloCglib.class);
    }

    @Test
    public void base() {
        mEnhancer.setCallback((FixedValue)() -> "FixedValue.loadObject");
        final HelloCglib proxy = (HelloCglib) mEnhancer.create();
        // HelloCGlib 的 toString() 的输出会被拦截，而输出 loadObject() 内容
        Assert.assertEquals("FixedValue.loadObject", proxy.toString());
        Assert.assertEquals("FixedValue.loadObject", proxy.sayWhat());

        // 无法拦截 final 方法
        LOG.info(proxy.getClass().toString());
        Assert.assertEquals("nope, i don't like you", proxy.sayNo());
    }

    /**
     * 使用 CallbackHelper 对指定方法进行拦截
     */
    @Test
    public void callbackHelper () {
        // HelloCglib 没有实现接口
        final Class[] interfaces = new Class[]{};
        final CallbackHelper callbackHelper = new CallbackHelper(HelloCglib.class, interfaces) {
            @Override
            protected Object getCallback(final Method method) {
                if ("sayWhat".equals(method.getName())) {
                    return (FixedValue)() -> "What a fuck day";
                }

                return NoOp.INSTANCE;
            }
        };
        mEnhancer.setCallbackFilter(callbackHelper);
        mEnhancer.setCallbacks(callbackHelper.getCallbacks());

        final HelloCglib proxy = (HelloCglib) mEnhancer.create();
        // 只拦截 sayWhat
        Assert.assertEquals("What a fuck day", proxy.sayWhat());
        // 其他的不拦截
        Assert.assertEquals("Hello CGLIB", proxy.toString());
    }

}