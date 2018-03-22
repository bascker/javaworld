package com.bascker.bsutil.bean;

/**
 * Address
 *
 * @author bascker
 */
public class Address {

    private String mCountry;
    private String mProvince;
    private String mCity;

    public Address (final String country, final String province, final String city) {
        mCountry = country;
        mProvince = province;
        mCity = city;
    }

    // -----------------------------------
    // Getter/Setter
    // -----------------------------------

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(final String country) {
        mCountry = country;
    }

    public String getProvince() {
        return mProvince;
    }

    public void setProvince(final String province) {
        mProvince = province;
    }

    public String getCity() {
        return mCity;
    }

    public void setCity(final String city) {
        mCity = city;
    }

    @Override
    public String toString() {
        return "Address{" +
                "mCountry='" + mCountry + '\'' +
                ", mProvince='" + mProvince + '\'' +
                ", mCity='" + mCity + '\'' +
                '}';
    }
}
