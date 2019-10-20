package com.seyrancom.consulting.config.root;

import com.seyrancom.consulting.config.ConfigUtils;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

@Configuration
@Profile({"dev"})
@EnableTransactionManagement
@EnableCaching
public class PersistenceConfigDev extends PersistenceConfigBase {

    // simple ConcurrentMapCache
/*    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();

        List<Cache> caches = new ArrayList<Cache>();
        caches.add(new ConcurrentMapCache("default"));
        *//*caches.add(new ConcurrentMapCache("books"));
        caches.add(new ConcurrentMapCache("myCacheName"));*//*
        //cacheManager.setCaches(Collections.singleton(new ConcurrentMapCache("transactions")));
        cacheManager.setCaches(caches);

        return cacheManager;
    }*/

    @Override
    protected Properties jpaProperties() {
        Properties properties = super.jpaProperties();
        /*Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));*/
        //String[] profiles = env.getActiveProfiles();
        /*Optional<String> pName = Optional.of(profileName);
        pName.filter(name -> !name.trim().isEmpty()).orElseThrow(IllegalArgumentException::new);*/
        //return ConfigUtils.loadProperties("/hibernate.dev.pg.properties", properties);
        return ConfigUtils.loadProperties("/hibernate.dev.h2.properties", properties);
    }
    /*
    @Bean
	public DataSource dataSource() {

		// no need shutdown, EmbeddedDatabaseFactoryBean will take care of this
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase db = builder
			.setType(EmbeddedDatabaseType.HSQL) //.H2 or .DERBY
			.addScript("db/sql/create-db.sql")
			.addScript("db/sql/insert-data.sql")
			.build();
		return db;
	}
    @Bean
    @Override
    public DataSource dataSource(AppConfig appConfig)    {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:file:./src/test/db/test");
        dataSource.setUsername("myapp");
        dataSource.setPassword("myapp");
        dataSource.setDriverClassName(env.getProperty("hibernate.connection.driver_class"));
        dataSource.setUrl(env.getProperty("db.url"));
        dataSource.setUsername(env.getProperty("db.username"));
        dataSource.setPassword(env.getProperty("db.password"));
        return dataSource;
    }*/
}