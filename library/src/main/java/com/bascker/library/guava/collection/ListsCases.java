package com.bascker.library.guava.collection;

import com.bascker.bsutil.CollectionHelper;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Lists 工具类使用
 *
 * @author bascker
 */
@Test
public class ListsCases {

    private static final Logger LOGGER = LoggerFactory.getLogger(ListsCases.class);

    public void testLists () {
        final List<String> list = Lists.newArrayList("my", "name", "is", "bascker");
        LOGGER.info(CollectionHelper.toString(list));
    }

}
