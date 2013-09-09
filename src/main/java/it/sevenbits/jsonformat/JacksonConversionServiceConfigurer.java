package it.sevenbits.jsonformat;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.List;

/**
 * Description
 *
 * @author Ivan Pastushenko
 * @version 1.0.0
 * Date: 07.09.2013
 */
@Component
public class JacksonConversionServiceConfigurer implements BeanPostProcessor {
    private final ConversionService conversionService;

    @Autowired
    public JacksonConversionServiceConfigurer(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof RequestMappingHandlerAdapter) {
            RequestMappingHandlerAdapter adapter = (RequestMappingHandlerAdapter) bean;
            List<HttpMessageConverter<?>> converters = adapter.getMessageConverters();
            for (HttpMessageConverter<?> converter : converters) {
                if (converter instanceof MappingJacksonHttpMessageConverter) {
                    MappingJacksonHttpMessageConverter jsonConverter = (MappingJacksonHttpMessageConverter) converter;
                    jsonConverter.setObjectMapper(new ConversionServiceAwareObjectMapper(conversionService));
                }
            }
        }
        return bean;
    }
}
