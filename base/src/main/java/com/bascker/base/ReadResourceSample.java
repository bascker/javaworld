package com.bascker.base;

import com.bascker.bsutil.Sample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 读取资源文件的案例
 *
 * 1.读取资源文件路径的方法
 *  1.1 Class.getResource(String name):
 *      1) name = "", 返回当前文件被编译后, 在 target/classes/PACKAGE 目录下搜索资源文件
 *      2) name = "/", 返回 target/classes
 *      3) name = "/" + FILENAME, 读取被编译后 target/classes 目录下 src 源码目录下的资源文件
 *
 *
 *  1.2 Class.getClassLoader().getResource(String name)
 *      1) name = "", 返回根目录
 *      2) name = FILENAME, 与 Class.getResource("/" + FILENAME) 相同
 *      3) name 不能以 "/" 开头, 即 name = "/" + FILENAME 是错误的, 因为类加载器的形式是从 ClassPath 根目录下进行搜索的
 *
 * Note:
 *   1.mvn 将工程编译后，会将作为 Sources Root 的 resources 目录下的文件放入 target/classes 目录下. 将作为 Test Sources Root
 *     的 resources 目录下的文件放入 target/test-classes 目录下.
 *   2.ClassPath 根目录在项目未编译时, 代表 src 目录, 而在编译后则代表 target/classes 目录
 *
 * @author bascker
 */
public class ReadResourceSample implements Sample {

    private static final Logger LOG = LoggerFactory.getLogger(ReadResourceSample.class);
    private static final String CONF = "conf.properties";

    public static void main(String[] args) {
        final ReadResourceSample sample = new ReadResourceSample();
        sample.start();
    }

    @Override
    public void start(final Object... args) {
        readByGetResource();
        readByClassLoader();
    }

    /**
     * 通过 Class.getResource(String name) 来读取资源文件
     */
    private void readByGetResource() {
        LOG.info("readByGetResource start");
        LOG.info("name = \"/\", path = {}", ReadResourceSample.class.getResource("/").getPath());
        LOG.info("name = \"\", path = {}", ReadResourceSample.class.getResource("").getPath());
        LOG.info("name = \"/{}\", path = {}", CONF, ReadResourceSample.class.getResource("/" + CONF).getPath());
        LOG.info("readByGetResource end");
    }

    private void readByClassLoader() {
        LOG.info("readByClassLoader start");
        LOG.info("name = \"\", path: {}", ReadResourceSample.class.getClassLoader().getResource("").getPath());
        LOG.info("name = \"{}\", path: {}", CONF, ReadResourceSample.class.getClassLoader().getResource(CONF).getPath());
        LOG.info("readByClassLoader end");
    }

}
