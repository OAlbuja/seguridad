package com.udla.spring.boot.backend.seguridad.apirest.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {
		AuthCredentials authCredentials = new AuthCredentials();

		try {
			authCredentials = new ObjectMapper().readValue(request.getReader(), AuthCredentials.class);
		} catch (IOException e) {
			// Manejo de la excepción
		}

		UsernamePasswordAuthenticationToken usernamePAT = new UsernamePasswordAuthenticationToken(
				authCredentials.getEmail(),
				authCredentials.getPassword(),
				Collections.emptyList());

		return getAuthenticationManager().authenticate(usernamePAT);
	}

	// @Override
	// protected void successfulAuthentication(HttpServletRequest request,
	// HttpServletResponse response,
	// FilterChain chain,
	// Authentication authResult) throws IOException, ServletException {

	// UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();
	// String token = TokenUtils.createToken(userDetails.getNombre(),
	// userDetails.getUsername());

	// response.addHeader("Authorization", "Bearer " + token);
	// response.getWriter().flush();

	// super.successfulAuthentication(request, response, chain, authResult);
	// }

	@Override
	protected void successfulAuthentication(HttpServletRequest request,
			HttpServletResponse response,
			FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();
		String token = TokenUtils.createToken(userDetails.getNombre(), userDetails.getUsername());

		// Crea un mapa para la respuesta
		Map<String, String> tokenMap = new HashMap<>();
		tokenMap.put("token", token);

		// Configura la respuesta para devolver un JSON
		response.setContentType("application/json");
		response.getWriter().write(new ObjectMapper().writeValueAsString(tokenMap));
		response.getWriter().flush();
	}

}
