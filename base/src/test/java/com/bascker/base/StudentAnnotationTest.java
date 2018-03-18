package com.bascker.base;

import com.bascker.base.annotation.Student;
import com.bascker.base.annotation.StudentAnnotationParser;
import com.bascker.bsutil.bean.Sex;
import org.junit.Test;

/**
 * StudentAnnotation & StudentAnnotationParse Unit Test
 *
 * @author bascker
 */
public class StudentAnnotationTest {

    @Student
    public void defaultStudent() {
    }

    @Student(name = "lisa", classRoom = "C1501", sex = Sex.FEMALE)
    public void lisa() {
    }

    @Test
    public void sample() {
        StudentAnnotationParser.parser(StudentAnnotationTest.class);
    }

}
