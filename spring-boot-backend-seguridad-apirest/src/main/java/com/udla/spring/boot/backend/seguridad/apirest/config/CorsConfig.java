package com.udla.spring.boot.backend.seguridad.apirest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.lang.NonNull;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NonNull CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4200")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}

//La clase CorsConfig es un ejemplo de cómo configurar las políticas de CORS (Cross-Origin Resource Sharing) 
//a nivel global en una aplicación Spring Boot. 
// Esta es una buena práctica cuando deseas aplicar una política de CORS consistente en toda tu aplicación, 
// en lugar de configurarla en cada controlador individualmente.

// no necesitas hacer nada adicional en tus controladores para que esta configuración surta efecto.