package it.sevenbits.jsonformat;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;

import java.io.IOException;

/**
 * ConvertingSerializer
 * @author Ivan Pastushenko
 * @version 1.0.0
 * Date: 07.09.2013
 */
final class ConvertingSerializer extends JsonSerializer<Object> {
    private final ConversionService conversionService;
    private final TypeDescriptor sourceType;

    public ConvertingSerializer(ConversionService conversionService, TypeDescriptor sourceType) {
        this.conversionService = conversionService;
        this.sourceType = sourceType;
    }

    @Override
    public void serialize(Object value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException, JsonProcessingException {
        jsonGenerator.writeString((String)conversionService.convert(value, sourceType, TypeDescriptor.valueOf(String.class)));
    }
}
