package com.bascker.library.guava;

import com.bascker.bsutil.bean.Person;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Guava Cache Cases
 *
 * 1.Guava Cache
 *  1.1 是一种<b>本地内存缓存、线程安全</b>的实现
 *  1.2 支持多种缓存过期策略
 *  1.3 两种缓存加载方式
 *      1) CacheLoader
 *      2) Callable
 *  1.4 使用场景
 *      1) 愿意通过消耗部分内存来提供应用程序的速度
 *      2) 缓存数据总量 < 本地服务器内存
 *  1.5 Guava Cache VS. ConcurrentMap
 *      1) ConcurrentMap: 一直保存所添加的元素, 直到数据被显示移除
 *      2) Guava Cache: 能自动回收元素
 *
 * 2.CacheLoader
 *  2.1 在缓存未命中的情况下, 通过默认方法加载缓存值
 *  2.2 适用场景: 缓存值计算的方法都是统一的
 *
 * 3.Callable
 *  3.1 通过指定的 Callable 运算, 返回缓存值.
 *  3.2 形成了一条工作链: 查询缓存-> 没有 -> 执行指定 Callable 运算 -> 结果存入缓存, 并返回缓存值
 *  3.3 适用场景: 对于不同的 key, 有不同的缓存计算方法
 *  3.4 Callable VS. CacheLoader: 最大区别就在于缓存值计算方法, Callable 可以对不同 key 有不同计算方法,
 *      而 CacheLoader 使用的是同一个计算方法.
 *
 * 4.缓存穿透
 *  4.1 定义: 当高并发条件下同时进行 get() 操作, 而此时缓存值已过期, 将导致大量线程都调用生成缓存值的方法(如从数据库读取),
 *           从而造成数据库雪崩的情况.
 *  4.2 Guava Cache 对缓存穿透进行了一定的控制，当大量线程使用相同的 key 获取缓存值时，只会有一个线程进入 load() 方法，
 *      而其他线程会被阻塞，直到进入 load() 的线程获取到缓存值,从而避免缓存穿透的问题.
 *  4.3 缺点:
 *      1) 每当某个缓存值过期时，会导致大量的请求线程被阻塞.
 *      2) 高并发下, "加锁 -> 取值 -> 释放锁"这一步骤会有一些性能损耗
 *
 * 5. 定时刷新缓存策略:
 *  5.1 定时刷新缓存值: 使用一个更新/刷新线程调用 load() 去更新缓存，而其他请求线程先返回旧值
 *  5.2 在 CacheBuilder.newBuilder() 时调用<b>refreshAfterWrite()</b>来实现定时刷新缓存。<b>缓存项只有在被检索时才会真正刷新</b>
 *  5.3 解决高并发下, 请求同一 key, 而该 key 缓存过期时多个线程阻塞的问题 #4.3
 *  5.4 缺点: 多 key 情况下，更新线程会被阻塞, 每一失效 key 的查询都会等待前一个失效 key 的刷新线程更新缓存完毕后才会启动
 *
 * 6.异步刷新
 *  6.1 将刷新缓存值的任务交给后台线程，所有的用户请求线程均返回旧的缓存值
 *  6.2 利用线程池执行缓存刷新任务, 解决 #5.4 的问题: 缓存池的维护虽然依旧需要依赖一个用户线程，但这种情况下当用户线程触发刷新，可以
 *      立即返回旧值.
 *
 * 7.缓存回收策略: 3 种
 *  7.1 基于容量回收: maximumSize(long)
 *  7.2 定时回收: expireAfterAccess() & expireAfterWrite()
 *  7.3 基于引用的回收: weakKeys() & weakValues() & softValues()
 *
 *
 * Guava 的更多使用查看<a hre="https://github.com/google/guava/wiki">Guava Wiki</a>
 *
 * @see com.google.common.cache.LocalCache                      对 {@link java.util.concurrent.ConcurrentMap} 的实现
 * @see com.google.common.cache.LocalCache.LocalLoadingCache
 * @author bascker
 */
public class CacheCases {

    private static final Logger LOG = LoggerFactory.getLogger(CacheCases.class);
    private static final long MAX_SIZE = 1000L;
    private static final long TTL = 10L;

    @Test
    public void testCacheLoader () throws ExecutionException {
        final CacheLoader<String, Person> cacheLoader = new CacheLoader<String, Person>() {

            // 当本地缓存命没有中时，调用 load() 获取结果并将结果缓存
            @Override
            public Person load(final String name) throws Exception {
                return findPerson(name);
            }

        };

        // build() 返回一个 LocalLoadingCache 实例
        final LoadingCache<String, Person> cache = CacheBuilder.newBuilder()
                .maximumSize(MAX_SIZE)
                .expireAfterAccess(TTL, TimeUnit.SECONDS)   // 若 TTL 时间内某元素没有被访问，则自动回收该元素
                .build(cacheLoader);

        // 查询缓存
        LOG.info("bascker: " + cache.get("bascker"));
    }

    @Test
    public void testCallable () throws ExecutionException {
        final Cache<String, Person> cache = CacheBuilder.newBuilder().maximumSize(MAX_SIZE).build();
        final Callable<Person> findBascker = () -> findPerson("bascker");
        final Person bascker = cache.get("bascker", findBascker);
        LOG.info("bascker: " + bascker);
    }


    /**
     * 定时刷新缓存策略
     */
    @Test
    public void testPeriodicRefresh () throws ExecutionException {
        final CacheLoader<String, Person> cacheLoader = new CacheLoader<String, Person>() {

            @Override
            public Person load(final String name) throws Exception {
                return findPerson(name);
            }

        };

        final LoadingCache<String, Person> cache = CacheBuilder.newBuilder()
                .maximumSize(MAX_SIZE)
                .refreshAfterWrite(TTL, TimeUnit.SECONDS)
                .build(cacheLoader);

        LOG.info("bascker: " + cache.get("bascker"));
    }

    /**
     * 异步刷新
     */
    @Test
    public void testAsynRefresh () {
        final ListeningExecutorService cacheRefreshPool =
                MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(20));
        final CacheLoader<String, Person> cacheLoader = new CacheLoader<String, Person>() {

            @Override
            public Person load(final String name) throws Exception {
                return findPerson(name);
            }

            /**
             * 重写 reload() 实现异步刷新
             * @param name
             * @param oldValue
             * @return
             * @throws Exception
             */
            @Override
            public ListenableFuture<Person> reload(final String name, final Person oldValue) throws Exception {
                return cacheRefreshPool.submit(() -> findPerson(name));
            }
        };
    }


    /**
     * 模拟数据库进行查询
     * @param name
     * @return
     */
    private Person findPerson (final String name) {
        final Person person = new Person(name);
        // 不要指定 seed, 否则多次调用生成同一个值
        final Random random = new Random();
        // 生成 [20, 56) 之间的值
        person.setAge(random.nextInt(36) + 20);

        return person;
    }

}
