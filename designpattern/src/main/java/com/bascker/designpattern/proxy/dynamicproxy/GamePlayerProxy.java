package com.bascker.designpattern.proxy.dynamicproxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * GamePlayer Proxy
 *
 * @author bascker
 */
public class GamePlayerProxy implements InvocationHandler {

    private static final Logger LOG = LoggerFactory.getLogger(GamePlayerProxy.class);
    private final Class mClass;
    // 被代理对象实例
    private Object mObject;

    public GamePlayerProxy(final Object object) {
        mObject = object;
        mClass = mObject.getClass();
    }

    /**
     * 动态产生一个被代理对象
     * @return
     */
    public Object getProxy () {
        return Proxy.newProxyInstance(mClass.getClassLoader(), mClass.getInterfaces(), this);
    }

    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        LOG.info(method.getName() + " params size: " + method.getGenericParameterTypes().length);
        return method.invoke(mObject, args);
    }

}