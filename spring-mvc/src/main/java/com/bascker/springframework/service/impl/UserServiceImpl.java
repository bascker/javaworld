package com.bascker.springframework.service.impl;

import com.bascker.springframework.dao.UserDao;
import com.bascker.springframework.model.User;
import com.bascker.springframework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User Service
 *
 * @author bascker
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User get(final String id) {
        return userDao.query(id);
    }

}
