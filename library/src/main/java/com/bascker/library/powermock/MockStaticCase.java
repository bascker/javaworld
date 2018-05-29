package com.bascker.library.powermock;

import com.bascker.library.powermock.model.User;
import com.bascker.library.powermock.model.UserFactory;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.testng.PowerMockObjectFactory;
import org.testng.Assert;
import org.testng.IObjectFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.ObjectFactory;
import org.testng.annotations.Test;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mockingDetails;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * 测试场景: 不直接 Mock 真实 User 的调用, 而是通过 mock 其内部实现方法的调用来产生 mock 数据
 *
 * @author bascker
 */
@Test
@PrepareForTest(UserFactory.class)
public class MockStaticCase {

    @BeforeMethod
    protected void before() {
        MockitoAnnotations.initMocks(this);
    }

    @ObjectFactory
    public IObjectFactory getObjectFactory() {
        return new PowerMockObjectFactory();
    }

    @Mock
    private UserFactory userFactory;

    public void test() {
        final User user = new User();
        Assert.assertEquals(user.getUser("paul").getName(), "paul");

        mockStatic(UserFactory.class);
        when(UserFactory.getInstance()).thenReturn(userFactory);
        when(userFactory.getUser(anyString())).thenReturn(new User("bascker"));
        Assert.assertTrue(mockingDetails(UserFactory.getInstance()).isMock());
        Assert.assertEquals(user.getUser("paul").getName(), "bascker");
    }

}
