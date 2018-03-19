package com.bascker.base;

import com.bascker.bsutil.bean.Person;

import java.util.Comparator;

/**
 * PersonComparator: Comparator Sample
 * 1.Comparator 接口
 *  1.1 比较器接口
 *  1.2 通过实现 Comparator 来定义我们自己的比较器，对集合对象或数组对象进行排序
 *
 * @author bascker
 */
public class PersonComparator implements Comparator<Person> {

    /**
     * 按年龄大小降序排序
     *
     * @param p1
     * @param p2
     * @return
     */
    @Override
    public int compare(final Person p1, final Person p2) {
        if (p1.getAge() > p2.getAge()) {
            return -1;
        } else if (p1.getAge() < p2.getAge()) {
            return 1;
        }

        return 0;
    }
}
