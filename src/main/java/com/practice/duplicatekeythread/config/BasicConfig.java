package com.practice.duplicatekeythread.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BasicConfig {

    @Bean
    public Logger logBean(){
        return LoggerFactory.getLogger(getClass());
    }
}
