package com.bascker.library.guava;

import com.bascker.bsutil.CollectionUtils;
import com.google.common.primitives.Ints;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Guava 的 com.google.common.primitives 提供了操作 JDK 基本数据类型的工具类
 *
 * 1.Ints
 *  1.1 asList()
 *  1.2 compare()
 *  1.3 join()
 *  1.4 max()
 *  1.5 tryParse()
 *
 * @author bascker
 */
public class PrimitiveCases {

    private static final Logger LOG = LoggerFactory.getLogger(PrimitiveCases.class);

    @Test
    public void testInts () {
        final List<Integer> list = Ints.asList(1,2,3,4);
        LOG.info(CollectionUtils.toString(list));
        LOG.info(String.valueOf(Ints.compare(1, 2)));
        Assert.assertEquals("1 2 3 4", Ints.join(" ", 1, 2, 3, 4));
        Assert.assertEquals(4, Ints.max(1, 2, 3, 4));
        Assert.assertEquals(1234, Ints.tryParse("1234"), 0);
    }

}
