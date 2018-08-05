package com.bascker.base;

import com.bascker.bsutil.Sample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Enumeration;
import java.util.Vector;
import java.util.stream.IntStream;

/**
 * Enumeration Sample
 *
 * 1.Enumeration
 *  1.1 JDK1.0 版本开始存在，基本被迭代器接口取代。新的代码实现中推荐使用迭代器
 *  1.2 方法
 *      1) hasMoreElements()
 *      2) nextElement()
 *  1.3 Enumeration VS. Iterator
 *      1) Iterator 支持迭代时移除某一个元素
 *      2) 不少集合类已经不支持 Enumeration 迭代了
 *
 * @author bascker
 */
public class EnumerationSample implements Sample {

    private static final Logger LOGGER = LoggerFactory.getLogger(EnumerationSample.class);

    public static void main(String[] args) {
        final Sample sample = new EnumerationSample();
        sample.start();
    }

    private Vector<String> vector;
    private Enumeration<String> enumeration;

    @Override
    public void start(final Object... args) {
        init();

        while (enumeration.hasMoreElements()) {
            LOGGER.info(enumeration.nextElement());
        }
    }

    private void init() {
        vector = new Vector<>();
        IntStream.range(1, 10).forEach(i -> vector.add("day" + i));

        enumeration = vector.elements();
    }

}
