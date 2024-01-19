package com.udla.spring.boot.backend.seguridad.apirest.services;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import com.udla.spring.boot.backend.seguridad.apirest.repositories.ContactoRepository;
import com.udla.spring.boot.backend.seguridad.apirest.services.IServices.IContactoService;

import lombok.NonNull;

import com.udla.spring.boot.backend.seguridad.apirest.dto.ContactoDTO;
import com.udla.spring.boot.backend.seguridad.apirest.models.Contacto;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ContactoServiceImp implements IContactoService {

    @Autowired
    private ContactoRepository contactoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ContactoDTO> findAll() {
        List<Contacto> contactos = contactoRepository.findAll();
        return contactos.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ContactoDTO findById(@NonNull Long id) {
        Contacto contacto = contactoRepository.findById(id).orElse(null);
        return contacto != null ? convertirADTO(contacto) : null;
    }

    @Override
    @Transactional
    public ContactoDTO save(ContactoDTO contactoDTO) {
    	Contacto contacto = convertirAEntidad(contactoDTO);
        if (contacto == null) {
            throw new IllegalArgumentException("El contacto no puede ser nulo");
        }
        contacto = contactoRepository.save(contacto);
        return convertirADTO(contacto);
    }

    @Override
    @Transactional
    public void delete(@NonNull Long id) {
    	contactoRepository.deleteById(id);
    }

    private ContactoDTO convertirADTO(Contacto contacto) {
        return modelMapper.map(contacto, ContactoDTO.class);
    }

    private Contacto convertirAEntidad(ContactoDTO contactoDTO) {
        return modelMapper.map(contactoDTO, Contacto.class);
    }

}
