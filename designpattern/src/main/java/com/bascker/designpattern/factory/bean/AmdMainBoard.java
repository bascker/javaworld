package com.bascker.designpattern.factory.bean;

/**
 * AMD MainBoard
 *
 * @author bascker
 */
public class AmdMainBoard implements MainBoard {

    private static final String BRAND = "AMD";

    @Override
    public String getBrand() {
        return BRAND;
    }

}
