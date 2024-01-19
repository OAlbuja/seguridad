package com.udla.spring.boot.backend.seguridad.apirest.dto;
import lombok.Data;

@Data
public class UsuarioPasswordUpdateDTO {
    private String currentPassword;
    private String newPassword;
}
