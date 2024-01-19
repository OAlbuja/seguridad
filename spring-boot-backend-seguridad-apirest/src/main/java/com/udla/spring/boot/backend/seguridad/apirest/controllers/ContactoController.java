package com.udla.spring.boot.backend.seguridad.apirest.controllers;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.udla.spring.boot.backend.seguridad.apirest.dto.ContactoDTO;
import com.udla.spring.boot.backend.seguridad.apirest.services.IServices.IContactoService;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ContactoController {

    private IContactoService contactoService;

    // Obtener todos los contactos
    @GetMapping("/contactos")
    public List<ContactoDTO> listarContactos() {
        return contactoService.findAll();
    }

    // Obtener un contacto por ID
    @GetMapping("/contactos/{id}")
    public ContactoDTO obtenerInteres(@PathVariable Long id) {
        return contactoService.findById(id);
    }

    // Crear un nuevo contacto
    @PostMapping("/contactos")
    @ResponseStatus(HttpStatus.CREATED)
    public ContactoDTO crearContactoDTO(@RequestBody ContactoDTO contactoDTO) {
        return contactoService.save(contactoDTO);
    }

    // Actualizar un contacto existente
    @PutMapping("/contactos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ContactoDTO actualizarContactoDTO(@RequestBody ContactoDTO contactoDTO, @PathVariable Long id) {
        ContactoDTO contactoActual = contactoService.findById(id);
        if (contactoActual != null) {
            contactoActual.setNombre(contactoDTO.getNombre());
            return contactoService.save(contactoActual);
        }
        // Aquí puedes manejar el caso de que el contacto no exista (por ejemplo,
        // lanzando una excepción)
        return null;
    }

        // Eliminar un contacto
    @DeleteMapping("/contactos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarContacto(@PathVariable Long id) {
        contactoService.delete(id);
    }
}