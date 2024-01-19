package com.udla.spring.boot.backend.seguridad.apirest.dto;
import lombok.Data;

@Data
public class UsuarioUpdateDTO {
    private String nombre;
    private String email;
}
