package com.bascker.library.jackson;

import com.bascker.bsutil.JsonUtils;
import com.bascker.library.jackson.bean.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * JsonProperty Case
 *
 * 1.@JsonProperty
 *  1.1 作用在属性上，用于将属性名序列化/反序列化成另一个名称
 *
 * @author bascker
 */
public class JsonPropertyCase {

    @Test
    public void testToString() throws JsonProcessingException {
        final User user = new User("0001", "bascker", "admin");
        final String json = JsonUtils.toString(user);
        System.out.println(json);
        assertTrue(json.contains("user_name"));
    }

    @Test
    public void testToObject() throws IOException {
        final String json = "{\"id\":\"0001\",\"user_name\":\"bascker\",\"user_pass\":\"admin\"}";
        final User user = JsonUtils.toObject(json, User.class);
        System.out.println(user);
        assertEquals("bascker", user.getName());
    }

}
