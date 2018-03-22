package com.bascker.library.jackson;

import com.bascker.library.jackson.bean.Student;
import com.bascker.library.jackson.bean.View;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * JsonView Annotation Cases
 *
 * 1.JsonView 注解
 *  1.1 jackson 中的一个注解，spring-mvc 也支持该注解
 *  1.2 用于控制对象在序列化时, 哪些属性可以被序列化: 默认情况下, Jackson 会序列化对象的所有属性
 *  1.3 使用 JsonView 时，需要执行 {@link ObjectMapper#disable(MapperFeature...)} 方法,
 *      取消默认的 {@link MapperFeature#DEFAULT_VIEW_INCLUSION} 动作, 该动作会将对象的所有属性进行序列化
 *
 * @see com.bascker.library.jackson.bean.Student    使用 @JsonView 注解的类
 * @see com.bascker.library.jackson.bean.View
 * @author backer
 */
public class JsonViewCases {

    private static final Logger LOG = LoggerFactory.getLogger(JsonViewCases.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private Student mStudent;
    private String json;

    @Before
    public void before () throws JsonProcessingException {
        mStudent = new Student("bascker", 24, "C1504");
        json = toJson(mStudent);
        LOG.info("默认的 JsonView 序列化的值: " + json);
    }

    /**
     * Jackson 序列化对象时，只序列化被 @JsonView 指定为 View.Public.class 的属性
     */
    @Test
    public void serializePublicView () throws JsonProcessingException {
        // 取消 Jackson 默认动作
        MAPPER.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);

        final String json = MAPPER.writerWithView(View.Public.class).writeValueAsString(mStudent);
        LOG.info("Public View: " + json);
    }

    /**
     * 只序列化被 @JsonView 指定为 View.Internal.class 的属性
     */
    @Test
    public void serializeInternalView () throws JsonProcessingException {
        MAPPER.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);

        final String json = MAPPER.writerWithView(View.Internal.class).writeValueAsString(mStudent);
        LOG.info("Internal View: " + json);
    }

    private String toJson (final Object object) throws JsonProcessingException {
        return MAPPER.writeValueAsString(object);
    }

    /**
     * 将 Student 的 JSON 字符串按 View.Public.class 反序列化成一个 Student
     * 这种情况下，必须有默认无参构造，否则会抛异常
     */
    @Test
    public void deserializePublicView () throws IOException {
        final Student student = MAPPER.readerWithView(View.Public.class)
                .forType(Student.class)
                .readValue(json);
        LOG.info(student.toString());
    }

    @Test
    public void deserializeInternalView () throws IOException {
        final Student student = MAPPER.readerWithView(View.Internal.class)
                .forType(Student.class)
                .readValue(json);
        LOG.info(student.toString());
    }

}
