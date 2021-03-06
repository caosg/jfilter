package com.jfilter.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * This class represents of JSON serialization from response of Spring Web Service
 */
public class FilterJsonConverter extends FilterConverter {

    /**
     * Creates a new instance of the {@link FilterJsonConverter} class.
     *
     * And specify supported media types
     */
    public FilterJsonConverter() {
        getSupportedMediaTypes().addAll(Arrays.asList(
                MediaType.APPLICATION_JSON,
                new MediaType("application", "json", Charset.defaultCharset()),
                new MediaType("application", "*+json")
        ));
    }

    /**
     * Returns JSON object mapper
     *
     * @return {@link ObjectMapper}
     */
    @Override
    public ObjectMapper getObjectMapper() {
        return Jackson2ObjectMapperBuilder.json().build();
    }
}