package com.udla.spring.boot.backend.seguridad.apirest.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.udla.spring.boot.backend.seguridad.apirest.models.Usuario;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	Optional<Usuario> findOneByEmail (String email);
}
