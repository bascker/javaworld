package com.bascker.springframework.model;

/**
 * Sex 枚举类
 *
 * @author bascker
 */
public enum Sex {

    UNKOWN(0), MALE(1), FEMALE(2);

    private int sex;

    Sex(final int sex) {
        this.sex = sex;
    }

    public Sex getSex() {
        Sex rs = UNKOWN;
        switch (sex) {
            case 1:
                rs = MALE;
                break;
            case 2:
                rs = FEMALE;
                break;
            default:
                break;
        }

        return rs;
    }
}
