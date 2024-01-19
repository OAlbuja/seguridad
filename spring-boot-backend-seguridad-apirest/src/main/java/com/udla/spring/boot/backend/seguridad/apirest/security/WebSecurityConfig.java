package com.udla.spring.boot.backend.seguridad.apirest.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

@Configuration
@AllArgsConstructor
public class WebSecurityConfig {

	private final UserDetailsService userDetailsService;
	private final JWTAuthorizationFilter jwtAuthorizationFilter;

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception {
		JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter();
		jwtAuthenticationFilter.setAuthenticationManager(authManager);
		jwtAuthenticationFilter.setFilterProcessesUrl("/login");

		return http
				.csrf().disable()
				.authorizeRequests()
				.anyRequest()
				.authenticated()
				.and()
				.httpBasic()
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.addFilter(jwtAuthenticationFilter)
				.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
		// .csrf(csrf -> csrf.disable())
		// .authorizeHttpRequests(auth -> auth
		// .requestMatchers("/login").permitAll()
		// .requestMatchers("/api/usuarios").permitAll() // Permite acceso sin
		// autenticación a
		// // /api/usuarios
		// .requestMatchers(HttpMethod.POST, "/api/usuarios").permitAll() // Permite
		// acceso sin
		// // autenticación a /api/usuarios
		// // .requestMatchers(HttpMethod.POST, "/api/usuarios").hasRole("ADMIN") //
		// // Ejemplo para agregar POST /api/usuarios
		// .anyRequest().authenticated())
		// .sessionManagement(session -> session
		// .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		// .addFilterBefore(jwtAuthorizationFilter,
		// UsernamePasswordAuthenticationFilter.class)
		// .build();
	}

	// @Bean
	// AuthenticationManager authenticationManager(AuthenticationConfiguration
	// authenticationConfiguration)
	// throws Exception {
	// return authenticationConfiguration.getAuthenticationManager();
	// }

	@Bean
	AuthenticationManager authManager (HttpSecurity http) throws Exception{
		return http.getSharedObject(AuthenticationManagerBuilder.class)
			.userDetailsService(userDetailsService)
			.passwordEncoder (passwordEncoder())
			.and()
			.build();

	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// Resto de la lógica de configuración...
	// public static void main(String[] args) {

	// System.out.println("Pass: " + new
	// BCryptPasswordEncoder().encode("jp123"));

	// }
}
