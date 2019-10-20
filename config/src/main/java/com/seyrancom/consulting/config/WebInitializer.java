package com.seyrancom.consulting.config;

import com.seyrancom.consulting.config.root.AppRoot;
import com.seyrancom.consulting.config.root.WebRoot;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    public static final String SERVLET_NAME = "MainServlet-dispatcher";
    public static final String DEFAULT_SERVLET_NAME = "DefaultServlet-dispatcher";

    @Override
    protected String getServletName() {
        return SERVLET_NAME;
        //return WebInitializer.class.getSimpleName() + ".dispatcher";
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{
                AppRoot.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{
                WebRoot.class
        };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        Filter[] singleton = {
                //Cross-origin resource sharing
                //new CORSFilter()
        };
        return singleton;
    }
}