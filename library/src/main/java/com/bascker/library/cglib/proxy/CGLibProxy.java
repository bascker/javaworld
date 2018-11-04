package com.bascker.library.cglib.proxy;

import com.bascker.bsutil.DateUtils;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * CGLib 实现动态代理
 *
 * 1.相比 JDK 自带的动态代理，它能够代理没有接口的类.
 * 2.CGLib 是方法级别的代理 == 方法拦截器
 *
 * @author bascker
 */
public class CGLibProxy implements MethodInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(CGLibProxy.class);

    private static final CGLibProxy instance = new CGLibProxy();

    public static CGLibProxy getInstance() {
        return instance;
    }

    /**
     * 创建代理对象
     * @param cls
     * @param <T>
     * @return
     */
    public <T> T getProxy(final Class<T> cls) {
        return (T) Enhancer.create(cls, this);
    }

    /**
     * 对目标进行方法拦截
     * @param obj
     * @param method
     * @param args
     * @param methodProxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(final Object obj, final Method method, final Object[] args, final MethodProxy methodProxy)
        throws Throwable {
        before();
        final Object result = methodProxy.invokeSuper(obj, args);
        after();

        return result;
    }

    private void before() {
        LOGGER.info("start at {}", DateUtils.now());
    }

    private void after() {
        LOGGER.info("end at {}", DateUtils.now());
    }

}