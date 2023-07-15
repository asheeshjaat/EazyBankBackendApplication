package com.m2p.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;
import java.security.SecureRandom;

@Configuration
public class JDBCUserDetailsSecurityConfig {

//    @Bean
//    public UserDetailsService jdbcUserDetailsManager(DataSource dataSource){
//        return new JdbcUserDetailsManager(dataSource);
//    }
@Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
         http.csrf().disable().
                 authorizeHttpRequests()
                .requestMatchers("/register/customer").permitAll()
                 .requestMatchers("/myAccount","/myBalance","/myCards","/myLoans").authenticated()
                .and()
                .httpBasic()
                .and().formLogin();
return http.build();
    }

@Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder( BCryptPasswordEncoder.BCryptVersion.$2Y,100);

    }xx
}
