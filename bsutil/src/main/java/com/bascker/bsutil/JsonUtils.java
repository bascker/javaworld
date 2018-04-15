package com.bascker.bsutil;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

/**
 * JSON 工具类
 *
 * @author bascker
 */
public class JsonUtils {

    private static final Logger LOG = LoggerFactory.getLogger(JsonUtils.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static Map<String, Object> toMap (final String jsonStr) throws IOException {
        return MAPPER.readValue(jsonStr, Map.class);
    }

    private JsonUtils () {}

}