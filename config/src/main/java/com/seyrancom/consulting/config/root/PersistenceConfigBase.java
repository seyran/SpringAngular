package com.seyrancom.consulting.config.root;

import com.seyrancom.consulting.config.ConfigUtils;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@EnableJpaRepositories(basePackages = "com.seyrancom.consulting.app.repository.jpa")
abstract public class PersistenceConfigBase extends AbstractConfigBase {

    public static final String PERSISTENCE_UNIT = "AppPersistenceUnit";
    public static final String HIBERNATE_BASE_PROPERTIES = "/hibernate.base.properties";

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /*    @Autowired
        private Environment env;*/

    /**
     * Bean post-processor that automatically applies persistence exception translation to any bean marked with Spring's @Repository annotation, adding a corresponding PersistenceExceptionTranslationAdvisor to the exposed proxy (either an existing AOP proxy or a newly generated proxy that implements all of the target's interfaces).
     *
     * @return
     */
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

/*    @Bean
    public HibernateExceptionTranslator hibernateExceptionTranslator() {
        return new HibernateExceptionTranslator();
    }*/

    @Bean
    public PersistenceAnnotationBeanPostProcessor persistenceAnnotationBeanPostProcessor() {
        PersistenceAnnotationBeanPostProcessor proc = new PersistenceAnnotationBeanPostProcessor();
       /* proc.setPersistenceContexts( new HashMap<String, String>()
                                 {{
                                     put("persistence/context1", "persistence/context1");
                                 }}
        );
        proc.setPersistenceUnits( new HashMap<String, String>()
                                 {{
                                     put(PERSISTENCE_UNIT, "persistence/context1");
                                 }}
        );*/
        proc.setDefaultPersistenceUnitName(PERSISTENCE_UNIT);
        return proc;
    }

/*

    */
/**
     * EhCacheCacheManager
     *
     * @return
     *//*

    @Bean
    public CacheManager cacheManager(net.sf.ehcache.CacheManager ehCacheCacheManager) {
        return new EhCacheCacheManager(ehCacheCacheManager);
    }
*/

    @Bean
    public EhCacheManagerFactoryBean ehCacheCacheManager(AppConfig appConfig) {
        EhCacheManagerFactoryBean factory = new EhCacheManagerFactoryBean();
        factory.setConfigLocation(new ClassPathResource("ehcache.xml"));
        factory.setCacheManagerName(appConfig.getEhcacheName());
        factory.setAcceptExisting(true);
        factory.setShared(false);
        //factory.afterPropertiesSet();
        return factory;
    }


    public DataSource dataSource() {
        return null;
    }

    /*
        @Bean
        public DataSourceInitializer dataSourceInitializer(DataSource dataSource)
        {
            DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
            dataSourceInitializer.setDataSource(dataSource);
            ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
            databasePopulator.addScript(new ClassPathResource("db.sql"));
            dataSourceInitializer.setDatabasePopulator(databasePopulator);
            dataSourceInitializer.setEnabled(Boolean.parseBoolean(initDatabase));
            return dataSourceInitializer;
        }
    */

    @Bean
    public EntityManagerFactory entityManagerFactory(AppConfig appConfig) {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        //factory.setJtaDataSource(dataSource());
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        factory.setJpaProperties(jpaProperties());
        factory.setPackagesToScan(appConfig.getPackageToScanForEntities());
        //factory.setSharedCacheMode(SharedCacheMode.ALL);
        //factory.setJpaDialect(new HibernateJpaDialect());
        factory.setPersistenceUnitName(PERSISTENCE_UNIT);
        logger.debug("getJpaPropertyMap={}", factory.getJpaPropertyMap());
        factory.afterPropertiesSet();
        return factory.getObject();
        //return factory;
    }

    protected Properties jpaProperties() {
        return ConfigUtils.loadProperties(HIBERNATE_BASE_PROPERTIES);
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;

    }

/*    @Bean
    public HibernateJpaSessionFactoryBean sessionFactory(EntityManagerFactory entityManagerFactory) {
        //((HibernateEntityManager) entityManagerFactory().getObject()).getSession();
        HibernateJpaSessionFactoryBean factory = new HibernateJpaSessionFactoryBean();
        factory.setEntityManagerFactory(entityManagerFactory);
        return factory;
    }*/
}