package com.udla.spring.boot.backend.seguridad.apirest.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}

//Crear una clase de configuración para ModelMapper como lo has hecho es una práctica común
//y recomendada en aplicaciones Spring Boot,
//especialmente cuando planeas usar ModelMapper en varios lugares de tu aplicación.