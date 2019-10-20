package com.seyrancom.consulting.config.root;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class ServiceConfig extends AbstractConfigBase {

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource resource = new ReloadableResourceBundleMessageSource();
        resource.setBasename("/messages/messages");
        resource.setDefaultEncoding("UTF-8");
        return resource;
    }

/*    @Bean
    public DefaultFormattingConversionService defaultConversionService() {
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
        //conversionService.addConverter(StringToDate.INSTANCE);
        return conversionService;
    }*/

  /*  @Bean
    public static ConversionService conversionService() {
        DefaultFormattingConversionService cs = new DefaultFormattingConversionService();
        cs.addConverter(String.class, Pattern.class, (Converter<String, Pattern>) Pattern::compile);
        return cs;
    }

    @Bean
    public SimpleMappingExceptionResolver simpleMappingExceptionResolver()
    {
        SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
        Properties mappings = new Properties();
        mappings.put("org.springframework.dao.DataAccessException", "error");
        resolver.setExceptionMappings(mappings);
        return resolver;
    }*/
}