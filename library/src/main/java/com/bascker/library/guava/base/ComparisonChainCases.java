package com.bascker.library.guava.base;

import com.bascker.bsutil.bean.Person;
import com.bascker.bsutil.bean.Sex;
import com.google.common.base.Preconditions;
import com.google.common.collect.ComparisonChain;
import org.testng.annotations.Test;

import java.util.function.BiFunction;

import static org.testng.Assert.assertEquals;

/**
 * ComparisionChain 的使用: 比较器，流式调用可以简化 Comparable 接口实现。
 *
 * @author bascker
 */
@Test
public class ComparisonChainCases {

    public void testCompare() {
        final BiFunction<Person, Person, Integer> isSamePerson = (p1, p2) -> {
            Preconditions.checkNotNull(p1, "The input param p1 is invalid");
            Preconditions.checkNotNull(p2, "The input param p2 is invalid");

            return ComparisonChain.start()
                    .compare(p1.getName(), p2.getName())
                    .compare(p1.getAge(), p2.getAge())
                    .compare(p1.getSex(), p2.getSex())
                    .result();
        };

        final Person p1 = new Person.Builder("bascker", 25, Sex.MALE).build();
        final Person p2 = new Person.Builder("bascker", 26, Sex.MALE).build();
        assertEquals(new Integer(-1), isSamePerson.apply(p1, p2));
    }

}