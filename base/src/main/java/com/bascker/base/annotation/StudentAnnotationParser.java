package com.bascker.base.annotation;

import com.bascker.bsutil.bean.Sex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Objects;

/**
 * Student 注解解析器
 *
 * @author bascker
 */
public class StudentAnnotationParser {

    private static final Logger LOG = LoggerFactory.getLogger(StudentAnnotationParser.class);

    public static void parser(Class clazz) {
        final StringBuffer sb = new StringBuffer();
        Arrays.stream(clazz.getMethods())
                .map(method -> method.getAnnotation(Student.class))
                .filter(Objects::nonNull)
                .forEach(student -> {
                    sb.append("{name: ").append(student.name()).append(", classRoom: ")
                            .append(student.classRoom()).append(", sex: ")
                            .append(student.sex() == Sex.UNKNOWN ? "保密" : student.sex() == Sex.MALE ? "男" : "女").append("}");
                    LOG.info(sb.toString());
                    sb.delete(0, sb.length());
                });
    }

}
