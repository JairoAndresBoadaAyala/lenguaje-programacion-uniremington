package com.example.demo.service;

import com.example.demo.dto.UpdateOrDeleteResponse;
import com.example.demo.dto.Usuario;
import com.example.demo.dto.UsuarioResponse;
import com.example.demo.repository.entity.UsuarioEntity;
import com.example.demo.service.interfaces.in.IUsuarioService;
import com.example.demo.service.interfaces.out.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements IUsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioResponse crear(Usuario usuario) {
        var usuarioRespuesta = usuarioRepository.crear(usuario);
        return UsuarioResponse.builder().id(usuarioRespuesta).mensaje("usuario creado correctamente").build();
    }

    public UsuarioEntity consultar(Long id) {
        return usuarioRepository.consultar(id);
    }


    @Override
    public UpdateOrDeleteResponse actualizar(Usuario usuario, Long id) {
        return usuarioRepository.actualizar(usuario, id);
    }

    @Override
    public UpdateOrDeleteResponse eliminar(Long id) {
        return usuarioRepository.eliminar(id);
    }
}
