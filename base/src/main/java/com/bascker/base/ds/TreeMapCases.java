package com.bascker.base.ds;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TreeMap;

/**
 * TreeMap Case.
 *
 * @author bascker
 */
public class TreeMapCases {

    private static final Logger LOG = LoggerFactory.getLogger(TreeMap.class);

    @Test
    public void base () {
        final TreeMap<String, Integer> map = new TreeMap<>();
        map.put("2017-08-19", 1);
        map.put("2017-08-18", 1);
        map.put("2017-08-20", 1);
        map.put("2017-08-16", 1);
        map.put("2016-08-16", 1);
        for (final String key : map.keySet()) {
            LOG.info(key);
        }
    }

}
