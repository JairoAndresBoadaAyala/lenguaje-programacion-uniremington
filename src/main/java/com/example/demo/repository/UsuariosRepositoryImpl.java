package com.example.demo.repository;

import com.example.demo.dto.UpdateOrDeleteResponse;
import com.example.demo.dto.Usuario;
import com.example.demo.exception.UsuarioException;
import com.example.demo.repository.entity.UsuarioEntity;
import com.example.demo.service.interfaces.out.UsuarioRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;


@Repository
public class UsuariosRepositoryImpl implements UsuarioRepository {


    ArrayList<UsuarioEntity> listaUsuario = new ArrayList<>();

    @Override
    public UsuarioEntity crear(Usuario usuario) {
        UsuarioEntity usuarioEntity = buildUsuario(usuario, null);

        listaUsuario.add(usuarioEntity);

        return usuarioEntity;
    }

    @Override
    public UsuarioEntity consultar(String id) {

        return listaUsuario.stream()
                .filter(r -> Objects.equals(id, r.getId()))
                .findFirst()
                .orElseThrow(() -> new UsuarioException("No se encontro usuario para actualizar"));

    }

    @Override
    public UpdateOrDeleteResponse eliminar(String id) {
        var consulta = listaUsuario.stream()
                .filter(r -> Objects.equals(id, r.getId()))
                .findFirst()
                .orElseThrow(() -> new UsuarioException("No se encontro usuario para eliminar"));
        var remove = listaUsuario.remove(consulta);
        if (remove) {
            return UpdateOrDeleteResponse.builder().mensaje("usuario con id " + id + "eliminado correctamente").build();
        } else {
            return UpdateOrDeleteResponse.builder().mensaje("no fue posible elimiar el usuario" + id).build();
        }
    }

    @Override
    public UpdateOrDeleteResponse actualizar(Usuario usuario, String id) {
        var consulta = listaUsuario.stream()
                .filter(r -> Objects.equals(id, r.getId()))
                .findFirst()
                .orElseThrow(() -> new UsuarioException("No se encontro usuario para actualizar"));
        var remove = listaUsuario.remove(consulta);
        if (remove) {
            listaUsuario.add(buildUsuario(usuario, id));
            return UpdateOrDeleteResponse.builder().mensaje("usuario con id " + id + "actualizado correctamente").build();
        } else {
            return UpdateOrDeleteResponse.builder().mensaje("no fue posible actualizar el usuario" + id).build();
        }
    }


    private UsuarioEntity buildUsuario(Usuario usuario, String id) {
        String idOrNew = "";
        if (id != null) {
            idOrNew = id;
        } else {
            idOrNew = UUID.randomUUID().toString();
        }
        return UsuarioEntity.builder()
                .id(idOrNew)
                .name(usuario.getName())
                .email(usuario.getEmail())
                .password(usuario.getPassword()).build();
    }
}
