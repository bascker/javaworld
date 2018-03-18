package com.bascker.base;

import com.bascker.bsutil.bean.Person;
import com.bascker.bsutil.bean.Sex;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

/**
 * PersonComparator Unit Test
 *
 * @author bascker
 */
public class PersonComparatorTest {
    private static final Logger LOG = LoggerFactory.getLogger(PersonComparatorTest.class);

    @Test
    public void test() {
        final List<Person> persons = Arrays.asList(new Person("bascker", 24, Sex.MALE),
                new Person("paul", 22, Sex.MALE), new Person("lisa", 21, Sex.FEMALE),
                new Person("john", 25, Sex.MALE));
        final StringBuffer sb = new StringBuffer();
        final Consumer<Person> action = person -> sb.append(person.getAge() + " ");
        persons.forEach(action);
        LOG.info("Before sort: " + sb.toString());

        sb.delete(0, sb.length());
        Collections.sort(persons, new PersonComparator());
        persons.forEach(action);
        LOG.info("After sort: " + sb.toString());
    }

}
