package com.udla.spring.boot.backend.seguridad.apirest.services.IServices;

import java.util.List;
import com.udla.spring.boot.backend.seguridad.apirest.dto.UsuarioDTO;
import com.udla.spring.boot.backend.seguridad.apirest.dto.UsuarioCreateDTO;
import com.udla.spring.boot.backend.seguridad.apirest.dto.UsuarioUpdateDTO;
import com.udla.spring.boot.backend.seguridad.apirest.dto.UsuarioPasswordUpdateDTO;

public interface IUsuarioService {
    List<UsuarioDTO> findAll();
    UsuarioDTO findById(Long id);
    UsuarioDTO save(UsuarioCreateDTO usuarioCreateDTO);
    void delete(Long id);
    UsuarioDTO updateUsuario(Long id, UsuarioUpdateDTO usuarioUpdateDTO);
    void cambiarContraseña(Long id, UsuarioPasswordUpdateDTO passwordUpdateDTO);
}
