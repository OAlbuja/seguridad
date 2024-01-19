package com.udla.spring.boot.backend.seguridad.apirest.services;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import com.udla.spring.boot.backend.seguridad.apirest.repositories.UsuarioRepository;
import com.udla.spring.boot.backend.seguridad.apirest.services.IServices.IUsuarioService;
import com.udla.spring.boot.backend.seguridad.apirest.dto.UsuarioCreateDTO;
import com.udla.spring.boot.backend.seguridad.apirest.dto.UsuarioDTO;
import com.udla.spring.boot.backend.seguridad.apirest.dto.UsuarioPasswordUpdateDTO;
import com.udla.spring.boot.backend.seguridad.apirest.dto.UsuarioUpdateDTO;
import com.udla.spring.boot.backend.seguridad.apirest.models.Usuario;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioServiceImp implements IUsuarioService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioDTO> findAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioDTO findById(@NonNull Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con ID: " + id));
        return convertirADTO(usuario);
    }

    // @Override
    // @Transactional
    // public UsuarioDTO save(UsuarioDTO usuarioDTO) {
    //     Usuario usuario = convertirAEntidad(usuarioDTO);
    //     if (usuario == null) {
    //         throw new IllegalArgumentException("El usuario no puede ser nulo");
    //     }
    //     usuario = usuarioRepository.save(usuario);
    //     return convertirADTO(usuario);
    // }

    @Override
    @Transactional
    public UsuarioDTO save(UsuarioCreateDTO usuarioCreateDTO) {
        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioCreateDTO.getNombre());
        usuario.setEmail(usuarioCreateDTO.getEmail());
        usuario.setPassword(passwordEncoder.encode(usuarioCreateDTO.getPassword()));

        Usuario usuarioGuardado = usuarioRepository.save(usuario);
        return convertirADTO(usuarioGuardado);
    }

    @Override
    @Transactional
    public void delete(@NonNull Long id) {
        usuarioRepository.deleteById(id);
    }

    @Transactional
    public UsuarioDTO updateUsuario(@NonNull Long id, UsuarioUpdateDTO usuarioUpdateDTO) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con ID: " + id));

        // Actualiza los campos necesarios
        usuarioExistente.setNombre(usuarioUpdateDTO.getNombre());
        usuarioExistente.setEmail(usuarioUpdateDTO.getEmail());

        // Guarda los cambios
        Usuario usuarioActualizado = usuarioRepository.save(usuarioExistente);
        return convertirADTO(usuarioActualizado);
    }

    @Transactional
    public void cambiarContraseña(@NonNull Long id, UsuarioPasswordUpdateDTO passwordUpdateDTO) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con ID: " + id));

        // Verifica que la contraseña actual sea correcta
        if (!passwordEncoder.matches(passwordUpdateDTO.getCurrentPassword(), usuario.getPassword())) {
            throw new BadCredentialsException("Contraseña actual incorrecta");
        }

        // Encripta y establece la nueva contraseña
        usuario.setPassword(passwordEncoder.encode(passwordUpdateDTO.getNewPassword()));

        // Guarda los cambios
        usuarioRepository.save(usuario);
    }

    private UsuarioDTO convertirADTO(Usuario usuario) {
        return modelMapper.map(usuario, UsuarioDTO.class);
    }

    private Usuario convertirAEntidad(UsuarioDTO usuarioDTO) {
        return modelMapper.map(usuarioDTO, Usuario.class);
    }

}
