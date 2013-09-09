package it.sevenbits.jsonformat;

import org.codehaus.jackson.map.introspect.AnnotatedMethod;
import org.codehaus.jackson.map.introspect.NopAnnotationIntrospector;
import org.codehaus.jackson.map.introspect.Annotated;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * FormatAnnotationIntrospector
 * @author Ivan Pastushenko
 * @version 1.0.0
 * Date: 07.09.2013
 */
public class FormatAnnotationIntrospector extends NopAnnotationIntrospector {
    private final ConversionService conversionService;

    public FormatAnnotationIntrospector(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public boolean isHandled(Annotation annotation) {
        return annotation.toString().startsWith("@org.springframework.format.annotation");
    }

    @Override
    public Object findDeserializer(Annotated annotaded) {
        if (annotaded instanceof AnnotatedMethod) {
            AnnotatedMethod method = (AnnotatedMethod)annotaded;
            TypeDescriptor typeDescriptor = getTypeDescriptorForDeserializer(method);
            for (Annotation annotation: typeDescriptor.getAnnotations()) {
                if(isHandled(annotation)) {
                    return new ConvertingDeserializer(conversionService, typeDescriptor);
                }
            }
        }
        return null;
    }

    public Object findSerializer(Annotated annotaded) {
        if (annotaded instanceof AnnotatedMethod) {
            AnnotatedMethod method = (AnnotatedMethod)annotaded;
            TypeDescriptor typeDescriptor = getTypeDescriptorForSerializer(method);
            for (Annotation annotation: typeDescriptor.getAnnotations()) {
                if(isHandled(annotation)) {
                    return  new ConvertingSerializer(conversionService, typeDescriptor);
                }
            }
        }
        return null;
    }

    private TypeDescriptor getTypeDescriptorForDeserializer(AnnotatedMethod method) {
        Assert.isTrue(method.getName().startsWith("set"), "Expected a setter method, but was " + method.getName());
        String fieldName = StringUtils.uncapitalize(method.getName().substring(3));
        Field field = ReflectionUtils.findField(method.getDeclaringClass(), fieldName);
        if (field != null) {
            return new TypeDescriptor(field);
        }
        return null;
    }

    private TypeDescriptor getTypeDescriptorForSerializer(AnnotatedMethod method) {
        String fieldName;
        if (method.getName().startsWith("get")) {
            fieldName = StringUtils.uncapitalize(method.getName().substring(3));
        } else if (method.getName().startsWith("is")) {
            fieldName = StringUtils.uncapitalize(method.getName().substring(2));
        } else {
            throw new IllegalArgumentException("Expected a getter method, but was " + method.getName());
        }
        Field field = ReflectionUtils.findField(method.getDeclaringClass(), fieldName);
        if (field != null) {
            return new TypeDescriptor(field);
        }
        return null;
    }
}
