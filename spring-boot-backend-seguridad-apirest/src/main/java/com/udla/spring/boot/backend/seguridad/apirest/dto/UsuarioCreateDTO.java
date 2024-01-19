package com.udla.spring.boot.backend.seguridad.apirest.dto;
import lombok.Data;

@Data
public class UsuarioCreateDTO {
    private String nombre;
    private String email;
    private String password;
}
