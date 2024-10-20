package com.donactiva.proyecto.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.donactiva.proyecto.model.Usuarios;

@Repository
public interface UsuariosRepository extends CrudRepository<Usuarios, Integer>{
    Usuarios findByCorreo(String correo);
    Usuarios findByIdUsuario(int idUsuario);
}
