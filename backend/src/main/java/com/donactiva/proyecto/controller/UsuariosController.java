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

@RestController
@RequestMapping("/api")
public class UsuariosController {

    @Autowired
    private UsuariosService usuarioService;

    //Métodos POST

    //Métodos GET

    @GetMapping("/LogIn")
    public String obtenerUsuarioPorEmail(@RequestParam String correo, @RequestParam String contraseña) {

        Usuarios usuario = usuarioService.obtenerUsuarioPorCorreo(correo);
        if (usuario != null && usuario.getContraseña().equals(contraseña)){
            String resultado = "Bienvenido, " + usuario.getNombre();
            return resultado;
        }

        return "Credenciales invalidas.";
    }

    @PostMapping("/signUp")
    public void guardarUsuario(@RequestBody Usuarios usuario){
        usuarioService.guardarUsuario(usuario);
    }
}