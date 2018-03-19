package com.bascker.bsutil.bean;

/**
 * Sex
 *
 * @author bascker
 */
public enum Sex {

    UNKNOWN(0), MALE(1), FEMALE(2);

    private final int mSex;

    Sex(int sex) {
        mSex = sex;
    }

}
