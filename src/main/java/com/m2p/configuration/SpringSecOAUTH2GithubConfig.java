package com.m2p.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SpringSecOAUTH2GithubConfig {

    @Bean
    SecurityFilterChain defaultSecurityChain(HttpSecurity http) throws  Exception{
        http.authorizeHttpRequests().anyRequest().authenticated().and().oauth2Login();
        return http.build();
    }
//
//    @Bean
//    public ClientRegistrationRepository clientRegistrationRepository(){
//        ClientRegistration clientRegis=clientRegistration();
//        return new InMemoryClientRegistrationRepository(clientRegis);
//    }
//
//
//    public ClientRegistration clientRegistration(){
//        return CommonOAuth2Provider.GITHUB.getBuilder("github").clientId("510c151aee4531e1b1e6").clientSecret("e02098b03ce0ff1127e0d1506e122051385b2a60")
//                .build();
//    }


}
