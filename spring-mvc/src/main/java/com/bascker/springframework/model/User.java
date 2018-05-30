package com.bascker.springframework.model;

import com.bascker.springframework.util.UserFactory;

/**
 * User Model
 *
 * @author bascker
 */
public class User {

    private String id;
    private String name;
    private int age;
    private Sex sex;

    public User() {}

    public User(final String name) {
        this.name = name;
    }

    public User build(final String name) {
        return UserFactory.getInstance().createUser(name);
    }

    private User(final Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.age = builder.age;
        this.sex = builder.sex;
    }

    public static class Builder {
        private String id;
        private String name;
        private int age;
        private Sex sex;

        public Builder() {}

        public Builder setId(final String id) {
            this.id = id;
            return this;
        }

        public Builder setName(final String name) {
            this.name = name;
            return this;
        }

        public Builder setAge(final int age) {
            this.age = age;
            return this;
        }

        public Builder setSex(final Sex sex) {
            this.sex = sex;
            return this;
        }

        public User build() {
            return new User(this);
        }
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

    public int getAge() {
        return age;
    }

    public void setAge(final int age) {
        this.age = age;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(final Sex sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }
}
