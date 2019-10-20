package com.seyrancom.consulting.config;

import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Sets up the Spring Security filter chain
 */
@Order(1)
public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer{
}