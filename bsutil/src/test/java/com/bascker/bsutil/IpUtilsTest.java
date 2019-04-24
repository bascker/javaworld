package com.bascker.bsutil;

import org.junit.Test;

import static com.bascker.bsutil.IpUtils.isContains;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IpUtilsTest {

    @Test
    public void testIsContain() {
        // d1(16) = 00000000 ‭11010001‬(2) ==> 最大 1101 1111(2) = DF(16)  ==> 最小是 11000000(2) = C0(16)
        assertTrue(isContains("2181:d3:a538:d1::/59", "2181:d3:a538:c0::"));
        assertTrue(isContains("2181:d3:a538:d1::/59", "2181:d3:a538:c3::"));
        assertTrue(isContains("2181:d3:a538:d1::/59", "2181:d3:a538:df::"));
        assertTrue(isContains("2181:d3:a538:d1::/59", "2181:d3:a538:d3::"));
        assertFalse(isContains("2181:d3:a538:d1::/59", "2181:d3:a538:ff::"));
        assertFalse(isContains("2181:d3:a538:ff::/59", "2181:d3:a538:c0::"));

        // 特殊用例
        assertTrue(isContains("::/128", "2181:d3:a538:c0::"));
        assertTrue(isContains("df::/112", "df::ffff"));
        assertTrue(isContains("df::/112", "df:f0::ffff"));
        assertTrue(isContains("df::/112", "df:f0::ffff"));
    }

}
