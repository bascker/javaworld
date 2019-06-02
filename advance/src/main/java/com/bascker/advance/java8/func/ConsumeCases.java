package com.bascker.advance.java8.func;

import com.bascker.bsutil.bean.Person;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.ObjIntConsumer;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Consume 系列接口案例
 *
 * @author bascker
 */
@Test
public class ConsumeCases {

    /**
     * Consumer: 接受 1 个参数，并不返回任何结果
     * BiConsumer: 接受 2 个参数，并不返回任何结果
     * DoubleConsumer: 接受 1 个 double 参数，并不返回任何结果
     * IntConsumer: 接受 1 个 int 参数，并不返回任何结果
     * LongConsumer: 接受 1 个 long 参数，并不返回任何结果
     */
    public void testBiConsumer() {
        final List<Integer> nums = new ArrayList<>();
        final BiConsumer<List, Integer> addToList = (list, num) -> list.add(num);
        addToList.accept(nums, 1);
        addToList.accept(nums, 2);
        assertEquals(Arrays.asList(1, 2), nums);
    }

    /**
     * ObjIntConsumer: 接受一个 T 类型和一个 int 类型的输入参数，无返回值。
     * ObjDoubleConsumer: 接受一个 T 类型和一个 double 类型的输入参数，无返回值。
     * ObjLongConsumer: 接受一个 T 类型和一个 long 类型的输入参数，无返回值。
     */
    public void testObjIntConsumer() {
        final Person person = new Person();
        final ObjIntConsumer<Person> setAge = (p, age) -> {
            if (age > 0 && age < 100) {
                p.setAge(age);
            }
        };
        setAge.accept(person, 99);
        assertTrue(99 == person.getAge());
    }

}