package com.bascker.bsutil;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * CollectionHelper Unit Test
 *
 * @author bascker
 */
public class CollectionHelperTest {

    private static final Logger LOG = LoggerFactory.getLogger(CollectionHelperTest.class);

    @Test
    public void testToArray () throws NoSuchMethodException {
        final List<String> namse = Arrays.asList("bascker", "johnnie", "lisa", "an");
        final String[] names = CollectionHelper.toArray(namse);
        LOG.info(Arrays.toString(names));

        final String[] rs = CollectionHelper.toArray(Collections.emptyList());
        LOG.info(Arrays.toString(rs));
    }

}
