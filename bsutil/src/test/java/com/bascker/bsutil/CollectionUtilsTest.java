package com.bascker.bsutil;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * CollectionUtils Unit Test
 *
 * @author bascker
 */
public class CollectionUtilsTest {

    private static final Logger LOG = LoggerFactory.getLogger(CollectionUtilsTest.class);

    @Test
    public void testToArray () throws NoSuchMethodException {
        final List<String> namse = Arrays.asList("bascker", "johnnie", "lisa", "an");
        final String[] names = CollectionUtils.toArray(namse);
        LOG.info(Arrays.toString(names));

        final String[] rs = CollectionUtils.toArray(Collections.emptyList());
        LOG.info(Arrays.toString(rs));
    }

}
