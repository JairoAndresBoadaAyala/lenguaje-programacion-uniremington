package com.example.demo.service.interfaces.in;

import com.example.demo.dto.UpdateOrDeleteResponse;
import com.example.demo.dto.Usuario;
import com.example.demo.dto.UsuarioResponse;
import com.example.demo.repository.entity.UsuarioEntity;

public interface IUsuarioService {
    UsuarioResponse crear(Usuario usuario);

    UsuarioEntity consultar(Long id);

    UpdateOrDeleteResponse actualizar(Usuario usuario, Long id);

    UpdateOrDeleteResponse eliminar(Long id);

}
