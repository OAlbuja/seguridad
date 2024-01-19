package com.udla.spring.boot.backend.seguridad.apirest.security;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.udla.spring.boot.backend.seguridad.apirest.models.Usuario;
import com.udla.spring.boot.backend.seguridad.apirest.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findOneByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("El usuario con email " + email + "no existe."));

		return new UserDetailsImpl(usuario);
	}

}
