package com.bascker.springframework.util;


import com.bascker.springframework.model.User;

/**
 * User Factory
 *
 * @author bascker
 */
public class UserFactory {

    private static final UserFactory instance = new UserFactory();

    public User createUser(final String name) {
        return new User(name);
    }

    public static UserFactory getInstance() {
        return instance;
    }

    private UserFactory() {}

}
