package com.example.demo.repository;

import com.example.demo.dto.UpdateOrDeleteResponse;
import com.example.demo.dto.Usuario;
import com.example.demo.exception.UsuarioException;
import com.example.demo.repository.entity.UsuarioEntity;
import com.example.demo.service.interfaces.out.UsuarioRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
@Repository
public class UsuariosRepositoryImpl implements UsuarioRepository {


    @PersistenceContext
    private EntityManager em;

    @Override
    public Long crear(Usuario usuario) {
        try {
            var usuarioEntity = UsuarioEntity.builder()
                    .name(usuario.getName())
                    .email(usuario.getEmail())
                    .password(usuario.getPassword())
                    .build();

            em.persist(usuarioEntity);

            return usuarioEntity.getId();
        } catch (Exception e) {
            throw new UsuarioException("ha ocurrido un error insertando el usuario :  {}" + e.getCause().toString());
        }
    }

    @Override
    public UsuarioEntity consultar(Long id) {
        try {
            var usuarioEntidad = (em.find(UsuarioEntity.class, id));

            if(usuarioEntidad != null) {
                return usuarioEntidad;
            } else {
                throw new UsuarioException("el usuario con id : " + id + "no existe");
            }

        } catch (Exception e) {
            throw new UsuarioException("ha ocurrido un error consultando el usuario : " + id + e.getCause().toString());
        }
    }


    @Override
    public UpdateOrDeleteResponse actualizar(Usuario usuario, Long id) {

        try {
            var usuarioEntity = em.find(UsuarioEntity.class, id);

            if (usuarioEntity != null) {
                usuarioEntity.setName(buildValue(usuario.getName(), usuarioEntity.getName()));
                usuarioEntity.setEmail(buildValue(usuario.getEmail(), usuarioEntity.getEmail()));
                usuarioEntity.setPassword(buildValue(usuario.getPassword(), usuarioEntity.getPassword()));

                em.merge(usuarioEntity);
                return UpdateOrDeleteResponse.builder()
                        .mensaje("usuario actualizado correctamente")
                        .build();
            } else {
                throw new UsuarioException("el usuario con id  : " + id + "no existe");
            }
        } catch (Exception e) {
            throw new UsuarioException("ha ocurrido un error actualizando el usuario : " + id + e.getCause().toString());
        }

    }

   @Override
    public UpdateOrDeleteResponse eliminar(Long id) {
       try {
           var usuarioEntity = em.find(UsuarioEntity.class, id);

           if (usuarioEntity != null) {
               em.remove(usuarioEntity);
               return UpdateOrDeleteResponse.builder()
                       .mensaje("usuario Eliminado correctamente")
                       .build();
           } else {
               throw new UsuarioException("el usuario con id  : " + id + "no existe");
           }
       } catch (Exception e) {
           throw new UsuarioException("ha ocurrido un error eliminando el usuario : " + id + e.getCause().toString());
       }
    }

    private String buildValue(String valorRequest, String valorBd) {

        return (valorRequest == null) ? valorBd : valorRequest;

    }
}
