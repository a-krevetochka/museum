package com.mgtu.museum.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ApplicationConfig {
    @Bean
    public ModelMapper ModelMapper(){
        return new ModelMapper();
    }
    @Bean
    public String backupDir(@Value("${backup.path}") String backupDir) {
        return backupDir;
    }

    @Bean
    public String dbUser(@Value("${spring.datasource.username}") String dbUser) {
        return dbUser;
    }

    @Bean
    public String dbName(@Value("${backup.dbName}") String dbName) {
        return dbName;
    }
}
