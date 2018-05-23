package com.bascker.library.powermock;

import com.bascker.library.powermock.model.Address;
import com.bascker.library.powermock.model.User;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mockingDetails;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * PowerMock 基础使用
 *
 * @author bascker
 */
@Test
public class PowerMockCases {

    private static final Logger LOG = LoggerFactory.getLogger(PowerMockCases.class);
    private List<Integer> nums;

    @BeforeClass
    private void beforeClass() {
        // 创建一个 Mock 对象: Mock 一个 List 接口实例
        nums = mock(List.class);
        LOG.info("[PowerMock] nums: " + nums);
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
        // 初始化 Mockito 的注解
        MockitoAnnotations.initMocks(this);
    }

    @Mock
    private List<String> names;

    public void test() {
        // 调用 nums.get() 时, 全部返回 6
        when(nums.get(anyInt())).thenReturn(6);
        Assert.assertEquals(nums.get(1).intValue(), 6);

        // 判断 @Mock 注解是否成功
        Assert.assertTrue(mockingDetails(names).isMock());
    }

    // --------------------------------------
    // 注入 Mock 对象
    // --------------------------------------

    @InjectMocks
    private User user;

    @Mock
    private Address address;

    public void testInjectMocks() {
        when(address.getCity()).thenReturn("HangZhou");
        LOG.info(user.toString());
    }

}
