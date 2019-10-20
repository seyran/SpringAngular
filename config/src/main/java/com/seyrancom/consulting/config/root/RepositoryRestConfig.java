package com.seyrancom.consulting.config.root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

import javax.annotation.PostConstruct;

@Configuration
@Import(RepositoryRestMvcConfiguration.class)
public class RepositoryRestConfig extends RepositoryRestConfigurerAdapter {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected AppConfig appConfig;

    @PostConstruct
    public void init() {
        logger.info(getClass().getSimpleName() + " is loaded");
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.setBasePath(appConfig.getRestBasePath());
    }
}