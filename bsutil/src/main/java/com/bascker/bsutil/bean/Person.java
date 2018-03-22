package com.bascker.bsutil.bean;

import java.net.URL;
import java.util.List;
import java.util.Objects;

/**
 * Person: equals() & hashCode() sample
 *
 * @author bascker
 */
public class Person {

    private String mName;
    private int mAge;
    private Sex mSex;
    private List<String> mHabits;
    private int[] mLuckyNums;
    private Address mAddress;
    private URL mGithub;

    public Person() {}

    public Person(final String name) {
        mName = name;
    }

    public Person(final String name, final int age, final Sex sex) {
        mName = name;
        mAge = age;
        mSex = sex;
    }

    // ---------------------------------------
    // Getter/Setter
    // ---------------------------------------

    public String getName() {
        return mName;
    }

    public void setName(final String name) {
        mName = name;
    }

    public int getAge() {
        return mAge;
    }

    public void setAge(final int age) {
        mAge = age;
    }

    public Sex getSex() {
        return mSex;
    }

    public void setSex(final Sex sex) {
        mSex = sex;
    }

    public List<String> getHabits() {
        return mHabits;
    }

    public void setHabits(final List<String> habits) {
        mHabits = habits;
    }

    public int[] getLuckyNums() {
        return mLuckyNums;
    }

    public void setLuckyNums(final int[] luckyNums) {
        mLuckyNums = luckyNums;
    }

    public Address getAddress() {
        return mAddress;
    }

    public void setAddress(final Address address) {
        mAddress = address;
    }

    public URL getGithub() {
        return mGithub;
    }

    public void setGithub(final URL github) {
        mGithub = github;
    }

    @Override
    public boolean equals(final Object o) {
        // 1.比较引用, 是否是同一对象
        if (this == o) {
            return true;
        }

        // 2.instanceOf 判断是否是 Person 对象
        if (o instanceof Person) {
            // 3.判断是否遵循自定义规则: 某些属性值相等，就认为两个对象相等
            final Person person = (Person) o;
            return person.mName == mName && person.mSex == mSex && person.mAge == mAge;
        }

        return false;
    }

    @Override
    public int hashCode() {
        final int FACTOR = 31;
        int result = 17;
        result = FACTOR * result + mName.hashCode();
        result = FACTOR * result + mSex.hashCode();
        result = FACTOR * result + (Objects.isNull(mAge) ? 0 : Integer.valueOf(mAge).hashCode());   // 允许 age 为空
        return result;
    }

    @Override
    public String toString() {
        return "Person{" +
                "mName='" + mName + '\'' +
                ", mAge=" + mAge +
                ", mSex=" + mSex +
                '}';
    }
}
