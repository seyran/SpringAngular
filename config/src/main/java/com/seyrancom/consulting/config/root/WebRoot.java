package com.seyrancom.consulting.config.root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Configuration
@EnableWebMvc
/*@PropertySources({
        @PropertySource("app.properties"),
})*/
@ComponentScan({"com.seyrancom.consulting"})
public class WebRoot implements WebMvcConfigurer {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /*    @Autowired
        private Environment env;*/
    @Autowired
    protected AppConfig appConfig;

    @PostConstruct
    public void init() {
        logger.info(getClass().getSimpleName() + " is loaded");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
        //registry.addResourceHandler("/webapp/**").addResourceLocations("/");

        registry.addResourceHandler("/css/**").addResourceLocations("/css/**");
        registry.addResourceHandler("/img*").addResourceLocations("/img*");
        registry.addResourceHandler("/js*").addResourceLocations("/js*");
        //registry.addResourceHandler("/resources*").addResourceLocations("classpath:/resources/");
        // do the classpath works with the directory under webapp?
        registry.addResourceHandler("*.css").addResourceLocations("/");
        registry.addResourceHandler("*.js").addResourceLocations("/");
        registry.addResourceHandler("*.html").addResourceLocations("/");
    }
/*
    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("");
        resolver.setSuffix("");
        return resolver;
    }
*/

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
        //configurer.enable(WebInitializer.DEFAULT_SERVLET_NAME);
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/", ".jsp");
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(true)
                .useRegisteredExtensionsOnly(false)
                .ignoreAcceptHeader(true)
                .mediaType("html", MediaType.TEXT_HTML)
                .mediaType("json", MediaType.APPLICATION_JSON)
                .defaultContentType(MediaType.TEXT_HTML);
    }

    @Bean
    public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
        List<ViewResolver> resolvers = new ArrayList<>();

        InternalResourceViewResolver r1 = new InternalResourceViewResolver();
        r1.setPrefix("/");
        r1.setSuffix(".jsp");
        r1.setViewClass(JstlView.class);
        r1.setOrder(1);
        resolvers.add(r1);

        ResourceBundleViewResolver r2 = new ResourceBundleViewResolver();
        r2.setBasename("/views/spring-views");
        r2.setOrder(0);
        resolvers.add(r2);

        JsonViewResolver r3 = new JsonViewResolver();
        resolvers.add(r3);

        ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
        resolver.setViewResolvers(resolvers);
        resolver.setContentNegotiationManager(manager);
        return resolver;
    }

    /**
     * View resolver for returning JSON in a view-based system. Always returns a
     * {@link org.springframework.web.servlet.view.json.MappingJackson2JsonView}.
     */
    public class JsonViewResolver implements ViewResolver {
        public View resolveViewName(String viewName, Locale locale)
                throws Exception {
            MappingJackson2JsonView view = new MappingJackson2JsonView();
            view.setPrettyPrint(true);
            return view;
        }
    }

  /*  @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        //resolver.setPrefix("");
        resolver.setOrder(1);
        resolver.setPrefix("/");
        //resolver.setPrefix("/WEB-INF/views/");
        resolver.setViewClass(JstlView.class);
        //resolver.setSuffix("");
        //resolver.setSuffix(".jsp");
        //resolver.setSuffix(".html");
        return resolver;
    }

    @Bean
    public ResourceBundleViewResolver resourceBundleViewResolver() {
        ResourceBundleViewResolver resolver = new ResourceBundleViewResolver();
        resolver.setBasename("/views/spring-views");
        resolver.setOrder(0);
        return resolver;
    }*/

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();

        //Set the maximum allowed size (in bytes) for each individual file.
        resolver.setMaxUploadSizePerFile(5242880);//5MB

        //You may also set other available properties.

        return resolver;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }

   /* @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api*//**")
                .allowedOrigins("http://domain2.com")
                .allowedMethods("PUT", "DELETE")
                .allowedHeaders("header1", "header2", "header3")
                .exposedHeaders("header1", "header2")
                .allowCredentials(false).maxAge(3600);
    }*/
}