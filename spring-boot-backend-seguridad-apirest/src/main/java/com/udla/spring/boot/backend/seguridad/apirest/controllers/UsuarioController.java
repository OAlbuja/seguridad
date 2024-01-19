package com.udla.spring.boot.backend.seguridad.apirest.controllers;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.udla.spring.boot.backend.seguridad.apirest.dto.UsuarioCreateDTO;
import com.udla.spring.boot.backend.seguridad.apirest.dto.UsuarioDTO;
import com.udla.spring.boot.backend.seguridad.apirest.dto.UsuarioPasswordUpdateDTO;
import com.udla.spring.boot.backend.seguridad.apirest.dto.UsuarioUpdateDTO;
import com.udla.spring.boot.backend.seguridad.apirest.services.IServices.IUsuarioService;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UsuarioController {

    private IUsuarioService usuarioService;

    // Obtener todos los Usuarios
    @GetMapping("/usuarios")
    public List<UsuarioDTO> listarUsuarios() {
        return usuarioService.findAll();
    }

    // Obtener un Usuario por ID
    @GetMapping("/usuarios/{id}")
    public UsuarioDTO obtenerInteres(@PathVariable Long id) {
        return usuarioService.findById(id);
    }

    // Crear un nuevo Usuario
    // @PostMapping("/usuarios")
    // @ResponseStatus(HttpStatus.CREATED)
    // public UsuarioDTO crearUsuarioDTO(@RequestBody UsuarioDTO usuarioDTO) {
    // return usuarioService.save(usuarioDTO);
    // }

    @PostMapping("/usuarios")
    public ResponseEntity<UsuarioDTO> crearUsuario(@RequestBody UsuarioCreateDTO usuarioCreateDTO) {
        UsuarioDTO nuevoUsuario = usuarioService.save(usuarioCreateDTO);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
    }

    // Actualizar un Usuario existente
    // @PutMapping("/usuarios/{id}")
    // @ResponseStatus(HttpStatus.OK)
    // public UsuarioDTO actualizarUsuarioDTO(@RequestBody UsuarioDTO usuarioDTO,
    // @PathVariable Long id) {
    // UsuarioDTO usuarioActual = usuarioService.findById(id);
    // // No es necesario verificar si usuarioActual es null, ya que findById
    // lanzará
    // // una excepción si no se encuentra
    // usuarioActual.setNombre(usuarioDTO.getNombre());
    // // Aquí puedes agregar actualizaciones a otros campos si es necesario
    // usuarioActual.setEmail(usuarioDTO.getEmail());
    // return usuarioService.save(usuarioActual);
    // }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<UsuarioDTO> actualizarUsuario(@PathVariable Long id,
            @RequestBody UsuarioUpdateDTO usuarioUpdateDTO) {
        UsuarioDTO usuarioActualizado = usuarioService.updateUsuario(id, usuarioUpdateDTO);
        return new ResponseEntity<>(usuarioActualizado, HttpStatus.OK);
    }

    // Eliminar un Usuario
    @DeleteMapping("/Usuarios/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarUsuario(@PathVariable Long id) {
        usuarioService.delete(id);
    }

    @PutMapping("/usuarios/{id}/cambiarContraseña")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> cambiarContraseña(@PathVariable Long id,
            @RequestBody UsuarioPasswordUpdateDTO passwordUpdateDTO) {
        usuarioService.cambiarContraseña(id, passwordUpdateDTO);
        return ResponseEntity.ok("Contraseña actualizada con éxito");
    }

}
