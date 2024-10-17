package com.donactiva.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.donactiva.proyecto.model.Usuarios;
import com.donactiva.proyecto.service.UsuariosService;
import com.donactiva.proyecto.util.JwtUtil;

@RestController
@RequestMapping("/api")
public class UsuariosController {

    @Autowired
    private UsuariosService usuarioService;

    @Autowired
    private JwtUtil jwtUtil;

    // Métodos GET
    
    @GetMapping("/login")
    public String login(@RequestParam String correo, @RequestParam String contraseña) {

        Usuarios usuario = usuarioService.obtenerUsuarioPorCorreo(correo);

        if (usuario != null && usuario.getContraseña().equals(contraseña)){
            return jwtUtil.generarToken(usuario.getNombre(), usuario.getCorreo(), usuario.getContraseña());
        }
        return "";
    }
    
    @PostMapping("/signup")
    public void guardarUsuario(@RequestBody Usuarios usuario){
        usuarioService.guardarUsuario(usuario);
    }
}