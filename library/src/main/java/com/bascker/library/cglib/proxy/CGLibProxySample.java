package com.bascker.library.cglib.proxy;

import com.bascker.bsutil.Sample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CGLibProxy 动态代理案例
 *
 * @author bascker
 */
public class CGLibProxySample implements Sample {

    private static final Logger LOGGER = LoggerFactory.getLogger(CGLibProxy.class);

    public static void main(String[] args) {
        final CGLibProxySample sample = new CGLibProxySample();
        sample.start();
    }

    @Override
    public void start(final Object... args) {
        final Hello proxy = CGLibProxy.getInstance().getProxy(HelloBascker.class);
        proxy.say();
    }

}
