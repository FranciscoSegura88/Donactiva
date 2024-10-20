package com.donactiva.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.donactiva.proyecto.model.Usuarios;
import com.donactiva.proyecto.service.UsuariosService;
import com.donactiva.proyecto.util.JwtUtil;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class UsuariosController {

    @Autowired
    private UsuariosService usuarioService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Métodos GET
    
    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestParam String correo, @RequestParam String contraseña) {

        Usuarios usuario = usuarioService.obtenerUsuarioPorCorreo(correo);

        if (usuario != null && passwordEncoder.matches(contraseña, usuario.getContraseña())){
            String token = jwtUtil.generarToken(usuario.getIdUsuario(), usuario.getNombre(), usuario.getCorreo(), usuario.getRol().name());
            return ResponseEntity.ok("Hola, "+ usuario.getNombre() +". Su token es: " +  token);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales invalidas");
    }
    
    @PostMapping("/signup")
    public ResponseEntity<String> guardarUsuario(@RequestBody Usuarios usuario){
        try{

            if(usuario.getCorreo() != null){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("El usuario ya existe");
            }

            usuario.setContraseña(passwordEncoder.encode(usuario.getContraseña()));
            usuarioService.guardarUsuario(usuario);

            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario creado exitosamente");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el usuario.");
        }
    }

}