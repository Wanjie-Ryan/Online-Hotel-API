package com.hotel.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

//configuring spring security in the AppConfig for user authentication.
// it is a configuration class therefore annotate it with configuration.
@Configuration
@EnableWebSecurity
public class AppConfig {

    // spring security
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

        return null;
    }


}
