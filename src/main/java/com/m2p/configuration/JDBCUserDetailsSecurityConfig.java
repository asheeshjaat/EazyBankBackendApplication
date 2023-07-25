package com.m2p.configuration;

import com.m2p.filter.JWTTokenGeneratorFilter;
import com.m2p.filter.JWTTokenValidatorFilter;
import com.m2p.filter.csrfCookiesFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import javax.sql.DataSource;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
public class JDBCUserDetailsSecurityConfig {

//    @Bean
//    public UserDetailsService jdbcUserDetailsManager(DataSource dataSource){
//        return new JdbcUserDetailsManager(dataSource);
//    }
@Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

     CsrfTokenRequestAttributeHandler requestHandler=new CsrfTokenRequestAttributeHandler();
    requestHandler.setCsrfRequestAttributeName("_csrf");


         http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                 .cors().configurationSource(new CorsConfigurationSource() {
             @Override
             public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                 CorsConfiguration config=new CorsConfiguration() ;
                 config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                 config.setAllowedHeaders(Collections.singletonList("*"));
                 config.setAllowCredentials(true);
                 config.setAllowedMethods(Collections.singletonList("*"));
                 config.setExposedHeaders(Collections.singletonList("Authorization"));//for exposing JWT token to web application
                 config.setMaxAge(3600L);
             return config;
             }
         }).and().csrf((csrf)->csrf.csrfTokenRequestHandler(requestHandler).ignoringRequestMatchers("/register").csrfTokenRepository((CookieCsrfTokenRepository.withHttpOnlyFalse())))
                 .addFilterAfter(new csrfCookiesFilter(), BasicAuthenticationFilter.class)
                 .addFilterAfter(new JWTTokenGeneratorFilter(),BasicAuthenticationFilter.class)
                 .addFilterBefore(new JWTTokenValidatorFilter(),BasicAuthenticationFilter.class)
                 .authorizeHttpRequests()
                     .requestMatchers("/register").permitAll()
                     .requestMatchers("/myAccount","/myBalance","/myCards","/myLoans").authenticated()
//                 .requestMatchers("/notices").hasAnyAuthority("USER","LOAN")--FOR AUTORITY PURPOSE
                        .requestMatchers("/notices").hasAnyRole("USER","ADMIN")
                       .and()
                      .httpBasic()
                      .and().formLogin();
return http.build();
    }

@Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();

    }
}
