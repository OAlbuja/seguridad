package com.udla.spring.boot.backend.seguridad.apirest.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.time.LocalDate;
import lombok.Data;

@Entity
@Table(name = "contacto")
@Data
public class Contacto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String celular;
    private String email;

    @Column(name = "fechanac")
    private LocalDate fechaNacimiento;

    private static final long serialVersionUID = 1L;
}
