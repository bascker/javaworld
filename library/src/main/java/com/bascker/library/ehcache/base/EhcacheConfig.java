package com.bascker.library.ehcache.base;

import com.bascker.bsutil.FileUtils;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.Configuration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.xml.XmlConfiguration;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.stream.LongStream;

/**
 * Ehcache: 配置 CacheManager & Cache, 使用 Ehcache 之前必须执行的步骤
 *
 * 1.配置方式: 2 种
 *  1.1 代码配置
 *      1) 创建 CacheManager 实例并初始化
 *      2) 创建 Cache 实例
 *      3) 使用 Cache
 *      4) 关闭 CacheManager
 *
 *  1.2 XML 文件配置
 *      1) 读取 XML 配置
 *      2) 利用 XML 文件创建 CacheManager 并初始化
 *      3) 同 1.1.3
 *
 * @author bascker
 */
public class EhcacheConfig {

    private static final Logger LOG = LoggerFactory.getLogger(EhcacheConfig.class);

    /**
     * Programmatic configuration for ehcache
     */
    @Test
    public void byProgram () {
        // 1. Create CacheManager Instance and init it
        // 1.1 CacheManagerBuilder.newCacheManagerBuilder().build() 就可以返回一个 cacheManager 实例
        // 1.2 withCache() 的连缀调用，可以在创建 CacheManager 实例时就预先创建一个 cache
        // 1.3 默认情况下，创建的 cacheManager 是未被初始化的，因此需要执行 init() 进行初始化操作。若想创建后自动初始化，则 build() 时传入参数 true
        final CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                .withCache("preConfigured",
                        CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class, ResourcePoolsBuilder.heap(10)))
                .build();
        cacheManager.init();

        // 2. Create Cache
        // 2.1 Cache 的创建需要 2 个参数：cache 对象的别名 & CacheConfiguration
        // 2.2 CacheConfiguration 的 3 个参数分别对应 key 的类型，value 的类型, 缓存池资源的配置
        // 2.3 ResourcePoolsBuilder.heap(10) 表示只能存储 10 个 key-value 键值对, 若已存满，则将存储新的键值对，移除旧的
        final Cache<Long, String> preConfigured = cacheManager.getCache("preConfigured", Long.class, String.class);
        LongStream.range(0, 20).boxed().forEach(i -> preConfigured.put(i, "v" + i));

        final Cache<Long, String> cache = cacheManager.createCache("cache", CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class, ResourcePoolsBuilder.heap(10)));
        cache.put(1L, "hello ehcache!");

        // 3. Get Value
        preConfigured.forEach(entry -> LOG.info("{ k: " + entry.getKey() + ", v: " + entry.getValue() + " }"));

        final String val = cache.get(1L);
        LOG.info("Get value " + val + " from cache by key " + 1L);

        // 4. Finish
        // 4.1 removeCache() 不仅会删除对应 cache 的引用，还会关闭它
        // 4.2 cacheManager.close() 可以使用 try-with-resources 来替换
        cacheManager.removeCache("preConfigured");
        cacheManager.close();
    }

    /**
     * XML Configuration
     */
    @Test
    public void byXML () {
        final URL url = FileUtils.getFileInResources("ehcache.xml");
        final Configuration conf = new XmlConfiguration(url);
        try (final CacheManager cacheManager = CacheManagerBuilder.newCacheManager(conf)) {
            cacheManager.init();
            final Cache<String, Integer> ageCache = cacheManager.getCache("numbers", String.class, Integer.class);
            ageCache.put("bascker", 24);
            LOG.info("bascker's age is " + ageCache.get("bascker"));
        }
    }

}
