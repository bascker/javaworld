package com.bascker.designpattern.factory.factorymethod.sample.product;

import java.util.HashMap;
import java.util.Map;

/**
 * Product Factory: 延迟初始化时工厂方法模式的一个扩展应用
 *
 * 1.延迟加载的工厂类
 *  1.1 延迟加载: Lazy initialization，一个对象被消费完后，并不立刻释放，工厂类保持其初始状态,等待被再次使用
 *  1.2 好处: 可用于对象初始化比较复杂的情况下，如硬件访问，这样可通过延迟加载降低对象的产生和销毁带来的复杂性
 *
 * @author bascker
 */
public class ProductFactory {

    private static final Map<String, Product> CACHE = new HashMap<>();

    public static synchronized Product newProduct (final Class clazz) throws IllegalAccessException, InstantiationException {
        Product product = null;
        final String className = clazz.getName();
        final String name = className.substring(className.lastIndexOf(".") + 1, className.length());

        if (CACHE.containsKey(name)) {
            product = CACHE.get(name);
        } else {
            product = (Product) clazz.newInstance();
            CACHE.put(name, product);
        }

        return product;
    }

}
