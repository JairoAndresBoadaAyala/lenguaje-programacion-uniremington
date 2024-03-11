package com.example.demo.controller;

import com.example.demo.dto.UpdateOrDeleteResponse;
import com.example.demo.dto.Usuario;
import com.example.demo.dto.UsuarioResponse;
import com.example.demo.repository.entity.UsuarioEntity;
import com.example.demo.service.interfaces.in.IUsuarioService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final IUsuarioService usuarioService;

    public UsuarioController(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping()
    public UsuarioResponse crearUsuario(@Valid @RequestBody Usuario usuario) {
        return usuarioService.crear(usuario);
    }

    @GetMapping("/{id}")
    public final UsuarioEntity obtenerUsuario(@PathVariable("id") Long id) {
        return usuarioService.consultar(id);
    }

    @PutMapping("/{id}")
    public final UpdateOrDeleteResponse actualizarUsuario(@Valid @RequestBody Usuario usuario, @PathVariable("id") Long id) {
        return usuarioService.actualizar(usuario, id);
    }

    @DeleteMapping("/{id}")
    public final UpdateOrDeleteResponse eliminarUsuario(@PathVariable("id") Long id) {
        return usuarioService.eliminar(id);
    }
}

