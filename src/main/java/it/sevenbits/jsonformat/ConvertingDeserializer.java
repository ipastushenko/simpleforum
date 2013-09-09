package it.sevenbits.jsonformat;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;

import java.io.IOException;

/**
 * ConvertingDeserializer
 * @author Ivan Pastushenko
 * @version 1.0.0
 * Date: 07.09.2013
 */
final class ConvertingDeserializer extends JsonDeserializer<Object> {
    private final ConversionService conversionService;
    private final TypeDescriptor targetType;

    public ConvertingDeserializer(ConversionService conversionService, TypeDescriptor targetType) {
        this.conversionService = conversionService;
        this.targetType = targetType;
    }

    @Override
    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {
        Object value = jsonParser.getText();
        TypeDescriptor sourceType = TypeDescriptor.forObject(value);
        return conversionService.convert(value, sourceType, targetType);
    }
}
