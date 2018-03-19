package com.bascker.advance.java8;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * 数据并行化：Java8 中并行化操作的函数都是以 parallelXXX 的形式表现的
 *
 * @author bascker
 */
public class ParallelSample {

    private static final Logger LOG = LoggerFactory.getLogger(ParallelSample.class);

    /**
     * 使用并行化数组操作初始化数组
     * @return
     */
    @Test
    public void parallelInitialize(){
        double[] values = new double[10];
        Arrays.parallelSetAll(values, i -> i);
        LOG.info(Arrays.toString(values));
    }

}
