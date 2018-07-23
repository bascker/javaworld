package com.bascker.base;

import com.bascker.bsutil.CollectionHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 泛型
 *
 * 1.无界通配符: ?
 *  1.1 <?> 表示接受任意类型的值
 *  1.2 局限: 直接使用 <?> 的对象是无法修改的内容的, 即 <?> 只能读，不能写
 *
 * 2.泛型上界: <? extends T>
 *  2.1 表示泛型类型可能是所指定的类型，或指定类型的子类
 *  2.2 局限: 只能读，不能写入有意义的类型(可写入 null, 因为 null 可以表示任何类型).
 *      因为编译器无法确定容器所持有的类型，无法安全的向其中加入有意义的元素
 *
 * 3.泛型下界: <? super T>
 *  3.1 表示泛型类型可能是所指定的类型，或指定类型的父类，直到 Object
 *  3.2 可读/写, 编译器可确定容器中元素最少也是 T 类型，因此可以安全的添加 T 及其子类
 *  3.3 局限: 从容器中读取出来的数据只能放入 Object 对象中. 因为 JVM 知道泛型下界容器存的是 T 及其父类，但不知道具体是哪种可能，
 *      为避免类型转换错误，故而直接使用最顶层的父类 Object 来接受一切可能值.
 *
 * 4.场景: PECS 原则, Producer Extends Consumer Super
 *  4.1 频繁往外读取内容的，适合用上界 Extends
 *  4.2 经常往里插入的，适合用下界 Super
 *  4.3 二者一般用作方法参数，用于替换容器引用。用作局部变量时，容器只有泛型上界，即无论是 extends, 还是 super, 都只能往容器中放入
 *      T 以及 T 的子类对象。当然 extends 容器只能在初始化时一次性放入，初始化后无法写， super 容器可以在初始化后放入
 *
 * 5.泛型方法: <T> T get(Class<T> clazz)
 *  5.1 <T>: 声明方法持有一个类型 T，表示该方法是泛型方法
 *  5.2 T: 方法返回值
 *
 * 6.泛型的好处:
 *  6.1 编译器可在编译阶段告诉开发者要存入的元素类型是否正确
 *  6.2 从容器中取出元素时不需要强转
 *
 * 7.泛型擦除:
 *  7.1 泛型信息只在编译时存在，编译完成后会被 java 编译器擦除掉。即运行时无法知道容器的泛型信息
 *  7.2 理由：向下兼容，保证泛型代码和无泛型的旧代码兼容
 *  7.3 缺点：运行时没法获取参数的泛型信息
 *
 * 8.Type 接口:
 *  8.1 随泛型而生的, 是 Class 的父接口，用于表示不确定的类
 *  8.2 Type 是 Java 中所有类型的超级接口，包括原始类型、参数类型、数组类型、类型变量和基本类型
 *
 * @author bascker
 */
public class Genericity {

    private static final Logger LOG = LoggerFactory.getLogger(Genericity.class);

    /**
     * 泛型通配符 ?
     *
     * @param list
     */
    public void wildcard(final List<?> list) {
        LOG.info(CollectionHelper.toString(list));
//        list.add(0);                               // 不可写, 编译出错
    }

    /**
     * 泛型上界
     *
     * @param numbers 接收 List<Number/Integer/Long/Short> 等容器引用
     */
    public void up(final List<? extends Number> numbers) {
        LOG.info(CollectionHelper.toString(numbers));
//        numbers.add(1);                            // 无法写入有意义的数值
        numbers.add(null);

        final Number n = numbers.get(0);
        LOG.info("numbers.get(0): " + n);
    }

    /**
     * 泛型下界
     *
     * @param list 接收 List<String/Object> 容器引用
     */
    public void down(final List<? super String> list) {
        LOG.info(CollectionHelper.toString(list));
//        String s = list.get(0);                    // 编译错误
        final Object obj = list.get(0);              // 可读, 但只能用 Object 接受泛型下界容器的值
        LOG.info("list.get(0): " + obj);
        list.add("ha ha ha");                        // 可写
//        list.add(new Object());                    // 可接收 List<Object>, 但无法直接写入 Object 对象, 因 Object 不是 String 的子类
    }

}
