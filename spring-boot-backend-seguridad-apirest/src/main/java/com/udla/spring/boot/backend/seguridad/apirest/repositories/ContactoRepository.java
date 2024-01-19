package com.udla.spring.boot.backend.seguridad.apirest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.udla.spring.boot.backend.seguridad.apirest.models.Contacto;


@Repository
public interface ContactoRepository extends JpaRepository<Contacto, Long>{

}