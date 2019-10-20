package com.seyrancom.consulting.config.root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import javax.annotation.PostConstruct;

@Configuration
@PropertySources(
        {
                @PropertySource("classpath:/app.properties"),
                @PropertySource("classpath:" + PersistenceConfigBase.HIBERNATE_BASE_PROPERTIES)
        })
//@Order(Ordered.HIGHEST_PRECEDENCE + 100)
public class AppConfig {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${app.version}")
    private String appVersion;

    @Value("${spring.profiles.active}")
    private String profileName;

    @Value("${entity.package.scan}")
    private String packageToScanForEntities;

    @Value("${ehcache.name}")
    private String ehcacheName;

    @Value("${spring.data.rest.basePath}")
    private String restBasePath;

    /*    @Autowired
        private Environment env;*/


    @PostConstruct
    protected void init() {
        logger.info(getClass().getSimpleName() + " is loaded");
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer getPropertyPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer p = new PropertySourcesPlaceholderConfigurer();
      /*  p.setLocations(new Resource[]{
            new ClassPathResource("/app.properties"),  new ClassPathResource(PersistenceConfigBase.HIBERNATE_BASE_PROPERTIES)
        });*/
        //p.setIgnoreUnresolvablePlaceholders(true);
        return p;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public String getProfileName() {
        return profileName;
    }

    public String getPackageToScanForEntities() {
        return packageToScanForEntities;
    }

    public String getEhcacheName() {
        return ehcacheName;
    }

    public String getRestBasePath() {
        return restBasePath;
    }
}
