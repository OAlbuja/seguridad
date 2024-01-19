package com.udla.spring.boot.backend.seguridad.apirest.security;
import lombok.Data;


@Data
public class AuthCredentials {
	private String email;
	private String password;
}
