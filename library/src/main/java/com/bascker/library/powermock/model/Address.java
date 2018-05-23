package com.bascker.library.powermock.model;

/**
 * Address Model
 *
 * @author bascker
 */
public class Address {

    private String city;

    public Address() {}

    public Address(final String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                '}';
    }
}
