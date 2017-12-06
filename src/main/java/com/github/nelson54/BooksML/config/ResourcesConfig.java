package com.github.nelson54.BooksML.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResourcesConfig {

    @Bean
    public ClassLoader getClassLoader() {
        return getClass().getClassLoader();
    }

}
