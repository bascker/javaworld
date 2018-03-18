package com.bascker.base;

import com.bascker.bsutil.bean.Person;
import com.bascker.bsutil.bean.Sex;
import org.junit.Assert;
import org.junit.Test;

/**
 * equals() & hashCode() sample
 * 1.equals:
 * 1.1 判断对象值是否相等
 * 1.2 重写 equals() 必须重写 hashCode(): 值可以一样，而引用可以不一样。因此 equals 相等, hashCode 不一定相等。
 * 对象比较时，先 hashCode() 判断，再根据 equals() 判断
 * 1.3 equals 重写步骤:
 * 1) 判断是否等于自身 this
 * 2) instanceOf 判断是否为该类对象
 * 3) 判断是否遵循自定义的规则: 属性是否相等
 * <p>
 * 2.hashCode:
 * 2.1 判断对象的 hash 值是否相等
 * 2.2 hash 函数设计越精妙，越能确保哈希表的每个 bucket 只有一个节点
 * <p>
 * 3.若想偷懒，可以直接使用代码开发工具提供的模板来覆写 equals() & hashCode()
 *
 * @author bascker
 * @see com.bascker.bsutil.bean.Person  重写 equals() & hashCode() 的类
 */
public class EqualSample {

    @Test
    public void sample() {
        final Person bascker = new Person("bascker", 24, Sex.MALE);
        final Person paul = new Person("paul", 22, Sex.MALE);
        final Person lisa = new Person("lisa", 21, Sex.FEMALE);
        final Person lisa2 = lisa;

        Assert.assertNotEquals(bascker, paul);
        Assert.assertNotEquals(paul, lisa);
        Assert.assertEquals(lisa, lisa2);
    }

}