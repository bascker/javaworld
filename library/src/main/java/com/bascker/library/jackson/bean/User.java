package com.bascker.library.jackson.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * User
 *
 * @author bascker
 */
public class User {

    private String id;

    @JsonProperty("user_name")
    private String name;

    @JsonProperty("user_pass")
    private String password;

    public User() {}

    public User(final String id, final String name, final String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
