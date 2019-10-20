package com.seyrancom.consulting.config.root;

import com.seyrancom.consulting.config.ConfigUtils;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

@Configuration
@Profile({"prod"})
@EnableTransactionManagement
@EnableCaching
public class PersistenceConfigProd extends PersistenceConfigBase {

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
        return ConfigUtils.loadProperties("/hibernate.prod.properties", properties);
    }

/*    @Override
    @Bean(destroyMethod = "close")
    public DataSource dataSource() {

        HikariConfig hikariConfig = new HikariConfig();
        //hikariConfig.setDriverClassName(env.getRequiredProperty("database.driver"));
      *//*  hikariConfig.setJdbcUrl(env.getRequiredProperty("db.url"));
        hikariConfig.setUsername(env.getRequiredProperty("db.username"));
        hikariConfig.setPassword(env.getRequiredProperty("db.password"));*//*

        //hikariConfig.setConnectionTestQuery(env.getRequiredProperty("connection.testQuery"));

        final HikariDataSource ds = new HikariDataSource(hikariConfig);

        //DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());

*//*
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty("database.driver"));
        dataSource.setUrl(env.getRequiredProperty("database.url"));
        dataSource.setUsername(env.getRequiredProperty("database.username"));
        dataSource.setPassword(env.getRequiredProperty("database.password"));
*//*
        return ds;
    }*/
}