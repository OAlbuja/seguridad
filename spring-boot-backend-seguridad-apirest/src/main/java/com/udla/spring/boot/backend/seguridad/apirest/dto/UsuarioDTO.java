package com.udla.spring.boot.backend.seguridad.apirest.dto;

import lombok.Data;

@Data
public class UsuarioDTO {
    private Long id;
    private String nombre;
    private String email;
}
