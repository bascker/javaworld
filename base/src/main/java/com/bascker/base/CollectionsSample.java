package com.bascker.base;

import com.bascker.bsutil.CollectionUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Collections 的使用：操作集合的工具类
 * @author backer
 */
public class CollectionsSample {

    private static final Logger LOG = LoggerFactory.getLogger(CollectionsSample.class);
    private static List<Integer> numList = new ArrayList<Integer>();
    private static List<Character> chList = new ArrayList<Character>();

    @Before
    public void before() {
        numList.addAll(Arrays.asList(111, 222, 201, 000, 123));
        chList.addAll(Arrays.asList('a', 'c', 'b', 'e', 'd'));
    }

    /**
     * 根据元素的自然顺序,对指定列表按升序(默认)进行排序, 可传入自定义的 Comparator 来定义排序顺序
     */
    @Test
    public void testSort() {
        Collections.sort(numList);
        Assert.assertEquals("0 111 123 201 222", CollectionUtils.toString(numList));

        Collections.sort(chList);
        Assert.assertEquals("a b c d e", CollectionUtils.toString(chList));
    }

    /**
     * shuffle()：混排，与 sort 相反，它打乱在一个 List 中可能有的任何排列的踪迹，让其乱序
     */
    @Test
    public void testShuffle() {
        Collections.shuffle(numList);
        LOG.info(CollectionUtils.toString(numList));

        Collections.shuffle(chList);
        LOG.info(CollectionUtils.toString(chList));
    }

    /**
     * reverse(): 反转
     */
    @Test
    public void testReverse() {
        Collections.reverse(numList);
        Assert.assertEquals("123 0 201 222 111", CollectionUtils.toString(numList));

        Collections.reverse(chList);
        Assert.assertEquals("d e b c a", CollectionUtils.toString(chList));
    }

    /**
     * fill(): 使用指定元素替换指定列表中的所有元素，与 Arrays 的 fill() 不同，它不能填充空列表
     */
    @Test
    public void testFill() {
        Collections.fill(numList, 1);
        Assert.assertEquals("1 1 1 1 1", CollectionUtils.toString(numList));


        List<Integer> newList = new ArrayList<Integer>();
        Collections.fill(newList, 2);
        Assert.assertEquals(0, newList.size());
    }

    /**
     * copy():
     * 1.拷贝, 目标 List 和源 List， 将源的元素拷贝到目标，并覆盖它的内容
     * 2.目标 List 至少与源一样长。如果它更长，则在目标 List 中的剩余元素不受影响。
     */
    @Test
    public void testCopy() {
        final List<Integer> dest = new ArrayList<>();
//		Collections.copy(dest, numList);                        // 报错，因为 dest 中 size = 0 < numList.size()
//		LOG.info(CollectionUtils.toString(dest));

        dest.addAll(Arrays.asList(1, 2, 3, 4, 5, 6));
        Collections.copy(dest, numList);
        Assert.assertEquals("111 222 201 0 123 6", CollectionUtils.toString(dest));
    }

    /**
     * min(), max()
     */
    @Test
    public void testMin() {
        Assert.assertEquals(0, Collections.min(numList).intValue());
        Assert.assertEquals('a', Collections.min(chList).charValue());
    }

    /**
     * lastIndexOfSubList(): 返回指定源列表中最后一次出现指定目标列表的起始位置
     * indexOfSubList()： 返回指定源列表中第一次出现指定目标列表的起始位置
     */
    @Test
    public void testLastIndexOfSubList() {
        Assert.assertEquals(1, Collections.lastIndexOfSubList(numList, Arrays.asList(222, 201)));
    }

    /**
     * rotate(): 根据指定的距离循环移动指定列表中的元素.若正数，则向右移动，否则向左移动
     */
    @Test
    public void testRotate() {
        Collections.rotate(numList, 1);
        Assert.assertEquals("123 111 222 201 0", CollectionUtils.toString(numList));

        Collections.rotate(numList, -1);
        Assert.assertEquals("111 222 201 0 123", CollectionUtils.toString(numList));
    }

    /**
     * binarySearch(): 二分查找(前提：集合有序)
     */
    @Test
    public void testBinarySearch() {
        List<String> list = new ArrayList<String>();
        list.addAll(Arrays.asList("aaa", "bbb", "ccc", "eea", "eeb"));
        Assert.assertEquals(3, Collections.binarySearch(list, "eea"));
    }

    /**
     * replaceAll(): 使用一个新值替换List对象所有旧值
     */
    @Test
    public void testReplaceAll() {
        Collections.replaceAll(numList, 222, 333);
        Assert.assertEquals("111 333 201 0 123", CollectionUtils.toString(numList));
    }

    /**
     * emptyXXX(): 返回一个空的、不可变的集合对象。注意：不可变
     */
    @Test
    public void testEmptyXXX() {
        List<Integer> list = Collections.emptyList();
        Assert.assertEquals(0, list.size());
//		list.addAll(numList);		// 因为不可变，所以不支持更改 list 的操作-->报错
    }

}
