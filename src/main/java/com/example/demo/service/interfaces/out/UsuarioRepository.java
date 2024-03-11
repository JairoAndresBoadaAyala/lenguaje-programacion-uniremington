package com.example.demo.service.interfaces.out;

import com.example.demo.dto.UpdateOrDeleteResponse;
import com.example.demo.dto.Usuario;
import com.example.demo.repository.entity.UsuarioEntity;

public interface UsuarioRepository {

    UsuarioEntity crear(Usuario usuario);

    UsuarioEntity consultar(String id);

    UpdateOrDeleteResponse eliminar(String email);

    UpdateOrDeleteResponse actualizar(Usuario usuario, String id);

}
