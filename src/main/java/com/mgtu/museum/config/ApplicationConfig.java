package com.mgtu.museum.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ApplicationConfig {
    @Bean
    public ModelMapper ModelMapper(){
        return new ModelMapper();
    }
}
