package com.bascker.base.annotation;

import com.bascker.bsutil.bean.Sex;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Student Annotation: annotation sample
 * 1.Target
 *  1.1 指定注解作用区间
 *
 * 2.Retention:
 *  2.1 指定注解生命周期, 默认为 CLASS
 *  2.2 自定义注解应该指定生命周期为 RUNTIME，若默认 CLASS，则在测试时无法获取到注解信息，因为此时注解信息在执行过程中将不可用
 *
 * @author bascker
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Student {
    String name() default "bascker";

    String classRoom() default "C1504";

    Sex sex() default Sex.MALE;

}
