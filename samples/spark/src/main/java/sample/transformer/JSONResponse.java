package sample.transformer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.zapodot.jackson.java8.JavaOptionalModule;
import spark.ResponseTransformer;

public class JSONResponse implements ResponseTransformer {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().registerModule(new JavaOptionalModule());

    @Override
    public String render(Object model) {
        try {
            return OBJECT_MAPPER.writeValueAsString(model);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}