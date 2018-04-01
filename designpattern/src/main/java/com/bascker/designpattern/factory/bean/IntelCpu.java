package com.bascker.designpattern.factory.bean;

/**
 * Intel CPU
 *
 * @author bascker
 */
public class IntelCpu implements Cpu {

    private static final String BRAND = "Intel";

    @Override
    public String getBrand() {
        return BRAND;
    }

}
