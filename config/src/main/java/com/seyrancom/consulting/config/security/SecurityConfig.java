package com.seyrancom.consulting.config.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    LoginSuccessHandler loginSuccessHandler;

    @Autowired
    private UserDetailsService userDetailsService;

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
        /**
         * This section defines the user accounts which can be used for
         * authentication as well as the roles each user has.
         */

        auth.inMemoryAuthentication()
                .withUser("user").password("user").roles(UserRole.USER.getType()).and()
                .withUser("admin").password("admin").roles(UserRole.USER.getType(), UserRole.ADMIN.getType());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**");
        web.ignoring().antMatchers("/resources/**"); // #3
        //web.ignoring().antMatchers("/authFailure");
    }

    protected void configure(HttpSecurity http) throws Exception {
        // temporary disable (Cross Site Request Forgery)
        http.csrf().disable();

        //Cross-Site Request Forgery (CSRF) is a enums of attack that occurs when a malicious Web site, email, blog, instant message, or program causes a user's Web browser to perform an unwanted action on a trusted site for which the user is currently authenticated
       /* CsrfTokenResponseHeaderBindingFilter csrfTokenFilter = new CsrfTokenResponseHeaderBindingFilter();
        http.addFilterAfter(csrfTokenFilter, CsrfFilter.class);*/

        //super.configure(http);

/*        http.csrf()
                .disable()
                // указываем правила запросов
                // по которым будет определятся доступ к ресурсам и остальным данным
                .authorizeRequests()
                .antMatchers("/resources*//**", "*//**").permitAll()
         .anyRequest().permitAll()
         .and();*/

        http.authorizeRequests().anyRequest().anonymous();
        //http.authorizeRequests().antMatchers("/login*").permitAll();
        // security none, filters none, access permitAll
       /* http
                .authorizeRequests()
                .antMatchers("/ping**")
                .permitAll()
                .and()

                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and();*/

/*
        http
                .authorizeRequests()
                .antMatchers("/resources/public*//**").permitAll()
         .antMatchers("/resources/img*//**").permitAll()
         .antMatchers("/resources/bower_components*//**").permitAll()
         .antMatchers(HttpMethod.POST, "/user").permitAll()
         .anyRequest().authenticated()
         .and()
         .formLogin()
         .defaultSuccessUrl("/index.html")
         .loginProcessingUrl("/authenticate")
         .usernameParameter("username")
         .passwordParameter("password")
         .successHandler(new AjaxAuthenticationSuccessHandler(new SavedRequestAwareAuthenticationSuccessHandler()))
         //.successHandler(loginSuccessHandler)
         .loginPage("/resources/public/login.html")
         .and()
         .httpBasic()
         .and()
         .logout()
         .logoutUrl("/logout")
         .logoutSuccessUrl("/resources/public/login.html")
         .permitAll();*/
/*
        if ("true".equals(System.getProperty("httpsOnly"))) {
            logger.info("launching the application in HTTPS-only mode");
            http.requiresChannel().anyRequest().requiresSecure();
        }*/


      /*  http.formLogin()
                // указываем страницу с формой логина
                .loginPage("/login")
                // указываем action с формы логина
                .loginProcessingUrl("/j_spring_security_check")
                // указываем URL при неудачном логине
                .failureUrl("/login?error")
                // Указываем параметры логина и пароля с формы логина
                .usernameParameter("j_username")
                .passwordParameter("j_password")
                // даем доступ к форме логина всем
                .permitAll();

        http.logout()
                // разрешаем делать логаут всем
                .permitAll()
                // указываем URL логаута
                .logoutUrl("/logout")
                // указываем URL при удачном логауте
                .logoutSuccessUrl("/login?logout")
                // делаем не валидной текущую сессию
                .invalidateHttpSession(true);*/





/*


        http
                //Configures form login
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login/authenticate")
                .failureUrl("/login?error=bad_credentials")
                //Configures the logout function
                .and()
                .logout()
                .deleteCookies("JSESSIONID")
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                //Configures url based authorization
                .and()
                .authorizeRequests()
                //Anyone can access the urls
                .antMatchers(
                        "/auth*/
/**",
 "/login",
 "/signup*/
/**",
 "/user/register*/
/**"
 ).permitAll()
 //The rest of the our application is protected.
 .antMatchers("*/
/**").hasRole("USER")
 //Adds the SocialAuthenticationFilter to Spring Security's filter chain.
 .and()
 .apply(new SpringSocialConfigurer());

 */

    }

    public enum UserRole {

        ANONYMOUS(0, "ROLE_ANONYMOUS"),
        USER(1, "USER"),
        ADMIN(0, "ADMIN");

        private final Integer id;
        private final String type;

        public Integer getId() {
            return id;
        }

        public String getType() {
            return type;
        }

        UserRole(Integer id, String type) {
            this.id = id;
            this.type = type;
        }
    }
}