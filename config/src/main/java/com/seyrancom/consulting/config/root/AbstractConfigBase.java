package com.seyrancom.consulting.config.root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

abstract public class AbstractConfigBase {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /*    @Autowired
        private Environment env;*/

    @Autowired
    protected AppConfig appConfig;

    @PostConstruct
    public void init() {
        logger.info(getClass().getSimpleName() + " is loaded");
    }

/*    @Bean
    protected AppConfig getAppConfig(AppConfig appConfig) {
        this.appConfig = appConfig;
        return appConfig;
    }*/
}