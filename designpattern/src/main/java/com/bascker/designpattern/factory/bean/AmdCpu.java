package com.bascker.designpattern.factory.bean;

/**
 * AMD CPU
 *
 * @author bascker
 */
public class AmdCpu implements Cpu {

    private static final String BRAND = "AMD";

    @Override
    public String getBrand() {
        return BRAND;
    }

}
