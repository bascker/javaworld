package com.bascker.designpattern.factory.bean;

/**
 * Intel MainBoard
 *
 * @author bascker
 */
public class IntelMainBoard implements MainBoard {

    private static final String BRAND = "Intel";

    @Override
    public String getBrand() {
        return BRAND;
    }

}
