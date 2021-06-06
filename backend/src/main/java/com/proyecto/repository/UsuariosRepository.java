package com.proyecto.repository;

import com.proyecto.models.UsuarioModels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Capa de Repositorio.
 * Definimos las querys que utilizaremos con usuarios.
 */

@Repository
public interface UsuariosRepository extends JpaRepository<UsuarioModels, Integer> {

    @Query(value = "select * from usuarios where user = ?1", nativeQuery = true)
    Optional<UsuarioModels> obtenerUsuario(String user);

    @Query(value = "select * from usuarios where user = ?1 and clave = ?2", nativeQuery = true)
    Optional<UsuarioModels> loguearUsuario(String user, String clave);

    @Query(value = "select * from usuarios where user = ?1 or mail = ?2", nativeQuery = true)
    Optional<UsuarioModels> comprobarUsuarioRepetido(String user, String mail);

    @Query(value = "select * from usuarios where mail = ?1", nativeQuery = true)
    Optional<UsuarioModels> recuperarUsuarioMail(String mail);

}
