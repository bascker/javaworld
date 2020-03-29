package com.bascker.base;

import com.bascker.bsutil.Sample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Outer 关键字
 *   outer vs. break:  break 只能打破被包裹的第一层循环，而 outer 可结束最外面一层循环
 *
 * @author bascker
 * @since v1.0
 */
public class OuterSample implements Sample {

    private static final Logger LOGGER = LoggerFactory.getLogger(OuterSample.class);

    @Override
    public void start(Object... args) {
        final int len = (int) args[0];
        outer:
        for (int i = 0; i < len; i ++) {
            for (int j = 0; j < len; j ++) {
                if (i == 1) {
                    break outer;            // 直接跳到
                }
                LOGGER.info("{i: {}, j: {}}", i, j);
            }
        }
    }

    public static void main(String[] args) {
        final OuterSample sample = new OuterSample();
        sample.start(3);
    }

}
