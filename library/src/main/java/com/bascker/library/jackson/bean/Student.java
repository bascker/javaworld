package com.bascker.library.jackson.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * Student Bean
 *
 * 1.JsonInclude 注解
 *  1.1 可控制哪些情况下, 被注解的属性能转换成 json
 *  1.2 {@link JsonInclude.Include#NON_NULL} 被注解的属性不为空时，才能转为 json
 *
 * 2.JsonProperty 注解
 *  2.1 指定属性和 json 映射的名称
 *
 * Note: 使用 Jackson 来处理对象时，对象属性名最好不要加上前缀(如 mName), 由于 Jackson 反射代码的问题，可能会造成一些影响
 *
 * @author bascker
 */
public class Student {

    @JsonView(View.Public.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;

    @JsonView(View.Public.class)
    private int age;

    @JsonView(View.Internal.class)
    @JsonProperty("class_name")
    private String className;

    public Student() {}

    public Student(final String name, final int age, final String className) {
        this.name = name;
        this.age = age;
        this.className = className;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(final int age) {
        this.age = age;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(final String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + this.name + '\'' +
                ", age=" + age +
                ", className='" + className + '\'' +
                '}';
    }
}
