package com.montani.exam.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class JsonHelper {
    private static final Logger logger = LoggerFactory.getLogger(JsonHelper.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * convert json to POJO
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static<T> T mapToObject(String json, Class<T> clazz) {

        T obj = null;
        try {
            obj = objectMapper.readValue(json, clazz);
        }catch (Exception e) {
            logger.error("something wrong parsing the string to json", e);
        }
        return obj;
    }

    /**
     * convert json Array object to POJO
     * @param json
     * @param typeReference
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> List<T> mapToObject(String json, TypeReference<List<T>> typeReference) throws IOException {
        List<T> list = objectMapper.readValue(json, typeReference);
        return list;
    }

    /**
     * convert POJO to json format
     * @param toSerialize
     * @return
     */
    public static String serializeObject(Object toSerialize) {
        try {
            return objectMapper.writeValueAsString(toSerialize);
        } catch (JsonProcessingException e) {
            logger.error("something wrong with serializing object", e);
        }
        return null;
    }
}
