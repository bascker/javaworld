package com.bascker.library.powermock.model;

/**
 * 提供给 {@link com.bascker.library.powermock.MockStaticCase} 使用
 *
 * @author bascker
 */
public class UserFactory {

    private static UserFactory instance = new UserFactory();

    public User getUser(final String name) {
        return new User(name);
    }


    public static UserFactory getInstance() {
        return instance;
    }

    private UserFactory() {}

}
