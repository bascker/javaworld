package com.bascker.library.ehcache;

import com.bascker.bsutil.Sample;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.PooledExecutionServiceConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.impl.config.persistence.CacheManagerPersistenceConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Disk Store
 *
 * @author bascker
 */
public class DiskStoreSample implements Sample {

    private static final Logger LOG = LoggerFactory.getLogger(DiskStoreSample.class);
    private CacheManager mCacheManager;

    public static void main(String[] args) {
        final DiskStoreSample sample = new DiskStoreSample();
        sample.start();
    }

    @Override
    public void start(final Object... args) {
        try {
            initCacheManager();
            final Cache<Long, String> cache1 = mCacheManager.getCache("cache1", Long.class, String.class);
            final Cache<Long, String> cache2 = mCacheManager.getCache("cache2", Long.class, String.class);
            cache1.put(1L, "bascker");
            cache2.put(2L, "an.");
        } finally {
            mCacheManager.close();
        }
    }

    private void initCacheManager () {
        mCacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                .using(PooledExecutionServiceConfigurationBuilder.newPooledExecutionServiceConfigurationBuilder()
                        .defaultPool("dflt", 0, 10)
                        .pool("defaultDiskPool", 1, 3)
                        .pool("cache2Pool", 2, 2)
                        .build())
                .with(new CacheManagerPersistenceConfiguration(new File(getStoragePath(), "cache")))
                .withDefaultDiskStoreThreadPool("defaultDiskPool")
                .withCache("cache1",
                        CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class,
                                ResourcePoolsBuilder.newResourcePoolsBuilder()
                                        .heap(10, EntryUnit.ENTRIES)
                                        .disk(10L, MemoryUnit.MB)))
                .withCache("cache2",
                        CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class,
                                ResourcePoolsBuilder.newResourcePoolsBuilder()
                                        .heap(10, EntryUnit.ENTRIES)
                                        .disk(10L, MemoryUnit.MB))
                        .withDiskStoreThreadPool("cache2Pool", 2))
                .build(true);
    }


    public String getStoragePath() {
        return "F:\\";
    }
}
