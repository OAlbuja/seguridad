package com.udla.spring.boot.backend.seguridad.apirest.services.IServices;
import java.util.List;
import com.udla.spring.boot.backend.seguridad.apirest.dto.ContactoDTO;

public interface IContactoService {
    List<ContactoDTO> findAll();
    ContactoDTO findById(Long id);
    ContactoDTO save(ContactoDTO contactoDTO);
    void delete(Long id);
}


