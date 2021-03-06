package com.bascker.advance.java8;

import com.bascker.bsutil.CollectionHelper;
import com.bascker.bsutil.bean.Person;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stream: 流操作
 * 1.stream(): 将集合对象转为<b>串行</b>数据流，Java8 中所有集合操作都可以利用 stream 来处理
 *
 * 2.操作 Stream 的 3 个阶段
 *  1) 创建一个Stream
 *  2) 指定的中间操作将初始 Stream 转化为其他 Stream: 生成一个新的 Stream 来处理初始数据
 *  3) 最终的操作会产生一个结果，在调用最终操作前都会延迟执行的。在这之后，stream 不会再被使用
 *
 * 2.Stream VS. Collection
 *  2.1 Stream 不存储集合元素
 *  2.2 Stream 操作不修改源数据，只是返回一个新的 Stream 来承载结果
 *  2.3 Stream 操作都会尽可能的进行<b>延迟加载</b>, 这意味着当需要使用结果的时候它才会运行
 *
 *
 * @author bascker
 */
public class StreamCases {

    private static final Logger LOG = LoggerFactory.getLogger(StreamCases.class);

    /**
     * Stream 的创建
     */
    @Test
    public void create() {
        final Stream<String> emptyStream = Stream.empty();                  // 创建空的 Stream

        // of(): 使用一组初始值生产新的 Stream
        final Stream<Integer> intStream = Stream.of(1, 2, 3, 4, 5);
        LOG.info(String.valueOf(intStream.filter(i -> i > 3).count()));

        // generator()
        Stream.generate(() -> "Hello, World");

        // iterator()
        Stream.iterate(BigInteger.ZERO, n -> n.add(BigInteger.ONE));
    }

    /**
     * 内部迭代
     */
    @Test
    public void innerIterate(){
        final List<String> names = Arrays.asList("bascker", "paul", "lisa", "john", "jessica");

        // 内部迭代：函数式编程，类似于 Python 的列表推导式
        final long num = names.stream().filter(name -> "bascker".equals(name)).count();
        Assert.assertEquals(1, num);

        // 惰性求值操作：此处无输出
        final Stream<String> stream = names.stream().filter(name -> {
            LOG.info(name);
            return "bascker".equals(name);
        });

        // 及早求值操作：有输出
        Assert.assertEquals(1, stream.count());
    }

    /**
     * collect(toList()) 方法：由 Stream 中的值生成一个列表，是个及早求值操作
     */
    @Test
    public void testCollect(){
        final List<String> collected = Stream.of("a", "b", "c").collect(Collectors.toList());
        Assert.assertEquals(Arrays.asList("a", "b", "c"), collected);
    }

    /**
     * map 操作
     */
    @Test
    public void testMap(){
        // 将字符串转为大写形式
        final List<String> collected = Stream.of("a", "b", "hello")
                .map(data -> data.toUpperCase())
                .collect(Collectors.toList());
        Assert.assertEquals(Arrays.asList("A", "B", "HELLO"), collected);

        final List<Person> persons = Arrays.asList(new Person("bascker"), new Person("paul"), new Person("lisa"));
        final List<String> names = persons.stream().map(Person::getName).collect(Collectors.toList());
        Assert.assertEquals("bascker paul lisa", CollectionHelper.toString(names));
    }

    /**
     * filter：遍历数据并检查其中的元素时，可考虑使用 Stream 中提供的新方法 filter
     */
    @Test
    public void testFilter(){
        final List<String> list = Stream.of("a", "a1", "1a")
                .filter(data -> data.startsWith("a"))
                .collect(Collectors.toList());
        Assert.assertEquals(Arrays.asList("a", "a1"), list);
    }

    /**
     * flatMap: 可用 Stream 替换值，然后将多个 Stream 连接成一个 Stream
     */
    @Test
    public void testFlatMap(){
        // 包含多个列表的 Stream
        final List<Integer> list = Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4))
                .flatMap(numbers -> numbers.stream())
                .collect(Collectors.toList());
        Assert.assertEquals(Arrays.asList(1, 2, 3, 4), list);
    }

    /**
     * Stream 上常用的操作之一就是求最大值和最小值
     */
    @Test
    public void testMaxAndMin(){
        final List<Integer> list = Arrays.asList(1, 3, 5, 7, 9);
        final int min = list.stream().min(Comparator.comparing(data -> data * 2)).get();
        Assert.assertEquals(1, min);

        final int max = list.stream().max(Comparator.comparing(data -> data * 2)).get();
        Assert.assertEquals(9, max);
    }

    /**
     * reduce：可以实现从一组值中生成一个值
     */
    @Test
    public void testReduce() {
        // 操作基本类型
        final int sum = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .reduce(0, (acc, element) -> acc + element);	    // acc 是累加器，保存累加结果。 element 是当前元素
        Assert.assertEquals(45, sum);

        // 操作对象
        final List<Point> points = Arrays.asList(new Point(1, 2), new Point(2, 3), new Point(3, 4));
        final Optional<Point> optional = points.stream().reduce((p1, p2) -> new Point(p1.x + p2.x, p1.y + p2.y));
        final Point total = optional.get();
        Assert.assertEquals(6, total.x);
        Assert.assertEquals(9, total.y);

        // 复用: 避免 lambda 每次 new 一个对象
        final Point point2 = new Point(0, 0);
        final Optional<Point> optional2 = points.stream().reduce((p1, p2) -> {
            point2.x = p1.x + p2.x;
            point2.y = p1.x + p2.y;

            return point2;
        });
        Assert.assertEquals(6, point2.x);
        Assert.assertEquals(9, point2.y);
    }

    static class Point {
        int x;
        int y;

        public Point(final int x, final int y) {
            this.x = x;
            this.y = y;
        }
    }


    /**
     * 串行统计
     */
    @Test
    public void serialCount () {
        // filter() 是中间操作，针对 list 转换的数据进行过滤, count() 是最终操作, 负责结果统计
        // Stream 操作不是按照引用的顺序来执行的, 在此处当 count() 请求第一个元素，filter() 开始请求元素，直到找到一个长度等于 4 的元素
        final long count = Stream.of("aaaa", "111", "22", "bbbb").filter(s -> s.length() == 4).count();
        Assert.assertEquals(2, count);
    }

    /**
     * 并行统计
     */
    @Test
    public void parallerCount () {
        final List<String> list = Arrays.asList("aaaa", "111", "22", "bbbb");
        final long count = list.parallelStream().filter(s -> s.length() == 4).count();
        Assert.assertEquals(2, count);
    }

}
