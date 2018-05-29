package com.bascker.library.powermock;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Matchers.anyString;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * PowerMockito & Junit Mock 静态方法
 *
 * @author bascker
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = StringUtils.class)
public class PowerMockJunitMockStaticCases {

    @Before
    public void before() {
        mockStatic(StringUtils.class);
    }

    @Test
    public void testIsEmpty() {
        when(StringUtils.isEmpty(anyString())).thenReturn(false);
        Assert.assertFalse(StringUtils.isEmpty(null));
    }

}
