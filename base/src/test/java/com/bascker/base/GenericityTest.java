package com.bascker.base;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * Genericity Unit Test
 *
 * @author bascker
 */
public class GenericityTest {

    private static final Genericity sample = new Genericity();
    private final List<Integer> nums = IntStream.range(1, 10).boxed().collect(Collectors.toList());

    @Test
    public void testWildcard() {
        sample.wildcard(nums);
        final List<?> list = Arrays.asList(1, "bascker", '?');
        sample.wildcard(list);
    }

    @Test
    public void testUp() {
        sample.up(nums);
        final List<Long> ls = LongStream.range(10, 20).boxed().collect(Collectors.toList());
        sample.up(ls);
//        sample.up(Arrays.asList("1"));            // 无法传入 List<String>
    }

    @Test
    public void testDown() {
        final List<String> strs = new ArrayList<>();
        strs.addAll(Arrays.asList("my", "name", "is", "bascker"));
        sample.down(strs);
//        sample.down(nums);                        // 无法传入 List<Integer>
        final List<Object> objects = new ArrayList<>();
        objects.addAll(Arrays.asList(new Object(), new Object()));
        sample.down(objects);
    }

}
