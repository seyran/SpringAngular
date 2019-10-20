package com.seyrancom.consulting.config.root;

import com.seyrancom.consulting.config.security.SecurityConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({AppConfig.class, SecurityConfig.class, InitConfig.class, PersistenceConfigDev.class, PersistenceConfigProd.class, ServiceConfig.class, RepositoryRestConfig.class})
public class AppRoot extends AbstractConfigBase {

}