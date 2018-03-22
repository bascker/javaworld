package com.bascker.base;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.Launcher;
import sun.misc.URLClassPath;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * ClassLoader Case
 *
 * 1.BootstrapClassLoader
 *  1.1 最顶层的类加载器
 *  1.2 用于加载 %JAVA_HOME%\lib 下的核心类库
 *
 * 2.ExtClassLoader
 *  2.1 扩展类加载器
 *  2.2 用于加载 %JAVA_HOME%\lib\ext 下的类库
 *
 * 3.AppClassLoader
 *  3.1 也叫做 SystemClassLoader
 *  3.2 用于加载应用 classpath 下的类文件
 *
 * 4.Launcher
 *  4.1 {@link Launcher} 是 JVM 的入口应用
 *  4.2 Launcher 初始化了 ExtClassLoader 和 AppClassLoader(看源码)
 *
 * 5.ClassLoader:
 *  5.1 是 URLClassLoader 的父类
 *  5.2 URLClassLoader 是 Launcher.ExtClassLoader 的父类
 *
 * @author bascker
 */
public class ClassLoaderCases {

    private static final Logger LOG = LoggerFactory.getLogger(ClassLoaderCases.class);

    @Test
    public void bootstrap() {
        showBootstrapCLByLauncher();
        showBootStrapCLByProperty();
    }

    @Test
    public void ext() {
        final ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        final URLClassLoader extClassLoader = (URLClassLoader) classLoader.getParent();
        final URL[] urls = extClassLoader.getURLs();
        LOG.info(ArrayUtils.toString(urls));
    }

    @Test
    public void app() {
        final URLClassLoader appClassLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
        final URL[] urls = appClassLoader.getURLs();
        LOG.info(ArrayUtils.toString(urls));
    }

    /**
     * 使用 Launcher 获取类加载路径
     */
    private void showBootstrapCLByLauncher() {
        final URLClassPath urlClassPath = Launcher.getBootstrapClassPath();
        final URL[] urls = urlClassPath.getURLs();
        LOG.info(ArrayUtils.toString(urls));
    }

    /**
     * 使用 System.getProperty() 获取
     */
    private void showBootStrapCLByProperty() {
        final String BOOTSTRAP = "sun.boot.class.path";
        final String bootClassPath = System.getProperty(BOOTSTRAP);
        LOG.info(bootClassPath);
    }
}
