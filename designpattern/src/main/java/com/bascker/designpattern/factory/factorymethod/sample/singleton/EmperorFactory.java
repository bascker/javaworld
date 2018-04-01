package com.bascker.designpattern.factory.factorymethod.sample.singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * EmperorFactory
 *
 * @author bascker
 */
public class EmperorFactory {

    private static final Logger LOG = LoggerFactory.getLogger(EmperorFactory.class);
    private static Emperor mInstance;

    static {
        try {
            final Class clazz = Emperor.class;
            final Constructor<Emperor> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            mInstance = constructor.newInstance();
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            LOG.error(e.getMessage());
        }
    }

    public static Emperor getInstance () {
        return mInstance;
    }

}
