package it.sevenbits.jsonformat;

import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;

/**
 * ConversionServiceAwareObjectMapper
 * @author Ivan Pastushenko
 * @version 1.0.0
 * Date: 07.09.2013
 */
public class ConversionServiceAwareObjectMapper extends ObjectMapper {
    @Autowired
    public ConversionServiceAwareObjectMapper(ConversionService conversionService) {
        AnnotationIntrospector introspector = AnnotationIntrospector.pair(new FormatAnnotationIntrospector(conversionService), DEFAULT_ANNOTATION_INTROSPECTOR);
        setAnnotationIntrospector(introspector);
    }
}
