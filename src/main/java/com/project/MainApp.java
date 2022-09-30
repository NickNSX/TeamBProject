package com.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class MainApp {
    public static void main(String[] args) {
        
        SpringApplication.run(MainApp.class, args);
    }
    
    @Bean
    public ObjectMapper jsonMapper() {
        return new ObjectMapper();
    }
}
