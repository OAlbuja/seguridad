package com.udla.spring.boot.backend.seguridad.apirest.dto;
import java.time.LocalDate;

import lombok.Data;

@Data
public class ContactoDTO {
    
    private Long id;    
    private String nombre;
    private String celular;
    private String email;
    private LocalDate fechaNacimiento;

}
