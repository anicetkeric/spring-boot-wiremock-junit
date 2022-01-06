package com.wiremock.junit.billingservice.utils;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.generate;
import static org.assertj.core.api.Assertions.assertThat;


/**
 * Utility class for testing REST controllers.
 */

public final class TestUtil {

    private TestUtil() {}

    private static final Logger LOGGER = LoggerFactory.getLogger(TestUtil.class);

    private static final ObjectMapper mapper = createObjectMapper();

    private static final String BASE_PAYLOAD_LOCATION = "src/test/resources/payload/";

    private static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }


    /**
     * Convert an object to JSON byte array.
     *
     * @param object the object to convert.
     * @return the JSON byte array.
     * @throws IOException
     */
    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        return mapper.writeValueAsBytes(object);
    }


    /**
     * @param clazz instance class
     * @param payloadFileName json file for payload
     * @param <T> Generic Class For parsing
     * @return the JSON byte array.
     * @throws IOException
     */
    public static <T> byte[] convertJsonFileContentToJsonBytes(Class<T> clazz, String payloadFileName) throws IOException {

        try {
            File payloadFile = new File(String.format("%s%s", BASE_PAYLOAD_LOCATION, payloadFileName));
            if(payloadFile.exists()){
                var instance = mapper.readValue(payloadFile, clazz);

                return convertObjectToJsonBytes(instance);
            }
        } catch (JsonParseException | JsonMappingException e) {
            LOGGER.warn("Error parsing integration test payload json: {}", e.getMessage());
        }
        return new byte[]{};
    }

}
