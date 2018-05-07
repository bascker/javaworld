package com.bascker.designpattern.singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 使用 Enum 实现单例模式
 *
 * 1.是实现单例模式的最佳方式
 * 2.无论是序列化还是多线程环境，都安全(序列化安全, 线程安全)
 *
 * @author bascker
 */
public enum EnumSingleton {

    INSTANCE;

    private static final Logger LOG = LoggerFactory.getLogger(EnumSingleton.class);

    public void say () {
        LOG.info("I am a Enum Singleton...");
    }

}
