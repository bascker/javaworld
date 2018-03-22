package com.bascker.base;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * Java 引用类型
 *
 * 1.强引用: StrongReference
 *  1.1 给变量置空后，才会回收该类变量的内存
 *
 * 2.软引用: SoftReference
 *  2.1 只有当内存不够时，才会回收这类变量的内存
 *  2.2 作用: 可以使 Java 应用更好的管理内存，稳定系统，防止内存溢出
 *  2.3 场景:
 *      1) 当需要处理一些占用内存较大、生命周期较长且使用不频繁的对象时，尽量使用软引用技术
 *      2) 需要使用经常用到的图片，可以使用 soft 应用类型
 *
 * 3.弱引用: WeakReference
 *  3.1 与 SoftReference 最大的不同在于: GC 回收时，软引用对象需要判断是否回收，弱引用对象直接回收
 *  3.2 场景: 常用于 Map 数据结构中, 主要适用于实现无法防止其键值被回收的规范化映射
 *
 * 4.虚引用: PhantomReference
 *  4.1 与 SoftReference/WeakReference 不同, PhantomReference 不影响对象的生命周期
 *  4.2 若一个对象与虚引用关联，则跟没有引用与之关联一样，任何时候都可被 GC 回收
 *  4.3 虚引用必须和引用队列关联使用，当 GC 准备回收一个对象时，若发现它还有虚引用，就会把这个虚引用加入到与之关联的引用队列中
 *
 * 5.小结:
 *  5.1 一般很少使用弱引用和虚引用，而使用软引用的情况较多，因为软引用可以加速 JVM 对垃圾内存的回收速度
 *  5.2 使用 SoftReference & WeakReference 来优化程序, 避免 OOM 问题
 *
 * @author bascker
 */
public class ReferenceCases {

    private static final Logger LOG = LoggerFactory.getLogger(ReferenceCases.class);

    @Test
    public void StrongRef() {
        // 创建了一个 StringBuffer 对象，并用变量 sb 存储对该对象的引用，这就是一个强引用
        StringBuffer sb = new StringBuffer();
        // 释放强引用
        sb = null;
    }

    @Test
    public void softRef() {
        Integer num = new Integer(6);
        LOG.info("num: " + num.toString());
        // 使用完后, 将 num 设置为 SoftReference，并释放强引用
        SoftReference<Integer> sr = new SoftReference<>(num);
        num = null;

        if (sr != null) {       // 当再次需要使用 num 时，若内存足够，即 JVM 还未回收 num 的软引用时，可通过 sf 获取 num
            num = sr.get();
        } else {                // 已被 JVM  回收
            num = new Integer(6);
            sr = new SoftReference<>(num);
        }
    }

    @Test
    public void weakRef() {
        Integer num = new Integer(8);
        LOG.info("num: " + num);
        // 置为 WeakReference，并释放强引用
        WeakReference<Integer> sr = new WeakReference<>(num);
        num = null;

        if (sr != null) {
            num = sr.get();
        } else {
            num = new Integer(8);
            sr = new WeakReference<>(num);
        }
    }

    @Test
    public void phantomRef() {
        final ReferenceQueue<String> queue = new ReferenceQueue<>();
        PhantomReference<String> pr = new PhantomReference<>("bascker", queue);
        LOG.info("name: " + pr.get());
    }

}
