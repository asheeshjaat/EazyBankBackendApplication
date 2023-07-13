package com.m2p.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

//Spring web application security context check during any api process the bean of security
    // securityfilterChain , framework itsself give this method , but a user can manage accordingly
    @Bean
    SecurityFilterChain defaultSecurityChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests().
//                anyRequest().denyAll().and().httpBasic().and().formLogin();//denied all request
//
//        return http.build();

        http.authorizeHttpRequests()
                .requestMatchers("/myAccount","/myBalance","/myCards","/myLoans").authenticated()
                .requestMatchers("/notices","/contact").permitAll()
                .and().httpBasic()//for postman api hitting
                .and().formLogin();//for html api hitting
        return http.build();


    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService(){
//        UserDetails admin= User.withDefaultPasswordEncoder()
//                .username("asheesh")
//                .password("12345")
//                .roles("admin")
//                .build();
//        UserDetails user=User.withDefaultPasswordEncoder()
//                .username("sumit")
//                .password("sumit12345")
//                .roles("read")
//                .build();
//        return new InMemoryUserDetailsManager(admin,user);

        InMemoryUserDetailsManager inMemoryUserDetailsManager=new InMemoryUserDetailsManager();
                UserDetails admin= User.withUsername("asheesh")
                .password("12345")
                .roles("admin")
                .build();
        UserDetails user=User.withUsername("sumit")
                .password("sumit12345")
                .roles("read")
                .build();
        inMemoryUserDetailsManager.createUser(admin);
        inMemoryUserDetailsManager.createUser(user);
        return inMemoryUserDetailsManager;

    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }


}
