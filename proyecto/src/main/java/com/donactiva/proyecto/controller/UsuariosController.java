package com.donactiva.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.donactiva.proyecto.model.Usuarios;
import com.donactiva.proyecto.service.UsuariosService;

@RestController
@RequestMapping
public class UsuariosController {

    @Autowired
    private UsuariosService usuarioService;

    @GetMapping("/forgotPassword")
    public Usuarios obtenerUsuarioPorEmail(@RequestParam String correo) {
        return usuarioService.obtenerUsuarioPorCorreo(correo);
    }
}
