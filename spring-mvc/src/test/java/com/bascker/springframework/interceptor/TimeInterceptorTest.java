package com.bascker.springframework.interceptor;

import com.bascker.springframework.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * TimeInterceptor Unit Test
 * @author bascker
 */
public class TimeInterceptorTest extends BaseContextTests {

    @Autowired
    private UserDao userDao;

    public void test() {
        System.out.println(userDao.query("1"));
    }


}
