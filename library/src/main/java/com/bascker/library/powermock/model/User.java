package com.bascker.library.powermock.model;

/**
 * User Model
 *
 * @author bascker
 */
public class User {

    private String name;

//    private Address address;
    private Address address = new Address("JiangSu");

    public User() {}

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(final Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", address=" + address.getCity() +
                '}';
    }
}
