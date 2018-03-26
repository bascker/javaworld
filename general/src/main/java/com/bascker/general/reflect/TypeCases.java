package com.bascker.general.reflect;

import com.bascker.bsutil.bean.Person;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;
import sun.reflect.generics.reflectiveObjects.TypeVariableImpl;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Type Cases
 *
 * 1.Type 接口
 *  1.1 是所有类型的父接口
 *  1.2 Type 体系的类型: 5 种
 *      1) 原始类型:   Class
 *      2) 基本类型:   Class
 *      3) 参数化类型: ParameterizedType, 即泛型
 *      4) 类型变量:   TypeVariable, 即泛型中的变量
 *      5) 数组类型:   GenericArrayType
 *
 *  1.2 子接口: 4 个
 *      1) ParameterizedType: 表示泛型类型
 *      2) TypeVariable:      表示泛型参数类型, 即泛型中的变量
 *      3) GenericArrayType:  表示数组泛型
 *      4) WildcardType:      表示通配符类型, 如 <? extends Number>, 泛型上下界
 *
 * 2.ParameterizedType
 *  2.1 参数化类型
 *  2.2 方法
 *      1) getActualTypeArguments(): 返回一个数组, Type 类型参数的实际类型, 只返回最外层 "<>" 中的内容
 *      2) getRawType(): 声明泛型的类/接口
 *      3) getOwnerType(): 获取泛型的拥有者, 如 Map.Entry 的泛型拥有着是 Map, 即内部类泛型的拥有者是外部类
 *
 * 3.TypeVariable
 *  3.1 类型变量
 *
 * 4.WildcardType
 *  4.1 通配符表达式, 泛型上下界, 不属于 Type 体系
 *  4.2 方法
 *      1) getUpperBounds(): 获取泛型上界
 *      2) getLowerBounds(): 获取泛型下界
 *
 * 5.GenericDeclaration
 *  5.1 声明类型变量的所有实体的公共接口, 定义了哪些地方可以定义类型变量（泛型）
 *  5.2 子类: 3 个, Class & Method & Constructor
 *  5.3 从 5.2 可知, 只有 3 个地方可以自定义泛型
 *
 * @author bascker
 */
public class TypeCases {

    private static final Logger LOG = LoggerFactory.getLogger(TypeCases.class);
    private Map<String, Person> mMap;
    private Map.Entry<String, Person> mEntry;
    private List<? super Number> mNumbers;
    private List<? extends Integer> mIntegers;

    @Test
    public void testParameterizedType () throws NoSuchFieldException {
        // 获取 map 属性
        final Field map = getField("mMap");
        map.setAccessible(true);

        // 获取 field 属性的泛型类型
        final Type type = map.getGenericType();
        Assert.assertTrue(type instanceof ParameterizedTypeImpl);

        // 强转为 ParameterizedType
        final ParameterizedType parameterizedType = (ParameterizedType) type;
        LOG.info("泛型实际类型: " + Arrays.toString(parameterizedType.getActualTypeArguments()));
        LOG.info("声明泛型的类/接口: " + parameterizedType.getRawType().getTypeName());
        LOG.info("Map 泛型的拥有者: " + parameterizedType.getOwnerType());

        final Field entry = getField("mEntry");
        final ParameterizedType entryParamType = (ParameterizedType) entry.getGenericType();
        LOG.info("Map.Entry 泛型的拥有者: " + entryParamType.getOwnerType().getTypeName());
    }

    @Test
    public void testTypeVariable () throws NoSuchFieldException {
        // 获取类型变量
        final Field field = BaseDao.class.getDeclaredField("t");
        field.setAccessible(true);
        final Type type = field.getGenericType();
        Assert.assertTrue(type instanceof TypeVariableImpl);

        // 获取声明该泛型变量的实体: 此处应是 BaseDao
        final TypeVariable typeVariable = (TypeVariable) type;
        final GenericDeclaration genericDeclaration = typeVariable.getGenericDeclaration();
        LOG.info(genericDeclaration.toString());
    }

    @Test
    public void testGenericArrayType () throws NoSuchFieldException {
        final Field array = BaseDao.class.getDeclaredField("array");
        array.setAccessible(true);
        final Type type = array.getGenericType();
        LOG.info(type.toString());
        Assert.assertTrue(type instanceof GenericArrayType);
        Assert.assertFalse(type instanceof ParameterizedTypeImpl);

        final GenericArrayType genericArrayType = (GenericArrayType) type;
        LOG.info(genericArrayType.getGenericComponentType().toString());
    }

    @Test
    public void testWildcardType () throws NoSuchFieldException {
        final Field numbers = getField("mNumbers");
        final ParameterizedType parameterizedType = (ParameterizedType) numbers.getGenericType();

        // 剥去泛型变量的 "<>", 获取泛型实际类型
        final Type[] types = parameterizedType.getActualTypeArguments();
        LOG.info(Arrays.toString(types));
        final WildcardType wildcardType = (WildcardType) types[0];
        LOG.info("<? extends Number> 上界: " + Arrays.toString(wildcardType.getUpperBounds()));
        LOG.info("<? extends Number> 下界: " + Arrays.toString(wildcardType.getLowerBounds()));

        final Field integers = getField("mIntegers");
        final ParameterizedType integersPT = (ParameterizedType) integers.getGenericType();
        final WildcardType integersWT = (WildcardType) integersPT.getActualTypeArguments()[0];
        LOG.info("<? super Integer> 上界: " + Arrays.toString(integersWT.getUpperBounds()));
        LOG.info("<? super Integer> 下界: " + Arrays.toString(integersWT.getLowerBounds()));
    }

    private Field getField (final String fieldName) throws NoSuchFieldException {
        final Field field = TypeCases.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        return field;
    }

    static class BaseDao<T> {
        private T t;
        private T[] array;
    }

}