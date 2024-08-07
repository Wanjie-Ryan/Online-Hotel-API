package com.hotel.api.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

//configuring spring security in the AppConfig for user authentication.
// it is a configuration class therefore annotate it with configuration.
@Configuration
@EnableWebSecurity
public class AppConfig {

    // spring security
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        // in the authorizeHTTPRequest, we define which endpoints should be whitelisted, secure and the endpoints that should be accessed by roles
        // the endpoint provided, api/admin/** is only accessible by users with the roles Owner and Admin
        // the endpoint api/** will be accessed by any authenticated users
        // in the last endpoint, any user can access the endpoint and need not be authenticated
        // the add filter before method checks whether the user has provided a jwt token or not
        http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).authorizeHttpRequests(Authorize -> Authorize.requestMatchers("/api/admin/**").hasAnyRole("Owner", "Admin").requestMatchers("/api/**").authenticated().anyRequest().permitAll()).addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class).csrf(csrf-> csrf.disable()).cors(cors-> cors.configurationSource(corsConfigurationSource()));

        return http.build();
    }

    // cors functionality, passing in the routes that will communicate with the BE
    private CorsConfigurationSource corsConfigurationSource() {

        return new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

                CorsConfiguration cfg = new CorsConfiguration();
                // allowed routes that communicate with the BE.
                cfg.setAllowedOrigins(Arrays.asList(
                        "http://localhost:3000",
                        "http://localhost:3001"
                ));

                // Allowed HTTP methods
                cfg.setAllowedMethods(Collections.singletonList("*"));
                // Allow the credentials
                cfg.setAllowCredentials(true);
                cfg.setAllowedHeaders(Collections.singletonList("*"));
                // the exposed headers is necessary so that we can be able to send the jwt in the headers.
                cfg.setExposedHeaders(Arrays.asList("Authorization"));
                cfg.setMaxAge(3600L);


                return cfg;
            }
        };

    }

    // here we set the password encoder, bcrypt.
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



}
