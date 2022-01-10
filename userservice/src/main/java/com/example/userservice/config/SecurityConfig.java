package com.example.userservice.config;

import com.example.securityconfig.config.TokenProvider;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.example.securityconfig") //Finds and uses components in securityconfig module
public class SecurityConfig {

}
