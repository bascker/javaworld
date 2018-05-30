package com.bascker.springframework.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理处理器
 *
 * 1.Proxy.newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h)
 *  1) loader: 被代理类的 ClassLoader
 *  2) interfaces: 被代理的接口
 *  3) h: 代理处理器
 *
 * @author bascker
 */
@Component
public class DynamicProxyHandler implements InvocationHandler {

    // 被代理对象
    private Object proxy;

    // 拦截器
    @Autowired
    private Interceptor interceptor;


    /**
     * 代理需要调用的方法
     *
     * @param proxy     代理类对象
     * @param method    被代理的接口方法
     * @param args      被代理接口方法的参数
     * @return          方法调用的结果
     * @throws Throwable
     */
    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        Object rs = null;

        interceptor.before();
        rs = method.invoke(proxy, args);
        interceptor.after();

        return rs;
    }


    /**
     * 动态生成一个代理类对象，并绑定被代理类和代理处理器
     *
     * @param proxy 被代理类对象
     * @return      代理类对象
     */
    public Object bind(final Object proxy) {
        this.proxy = proxy;
        final Class clazz = this.proxy.getClass();

        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }

}
