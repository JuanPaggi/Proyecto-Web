package com.proyecto.repository;

import com.proyecto.models.ComentarioModels;

import com.proyecto.models.UsuarioModels;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Capa de Repositorio.
 * Definimos las querys que utilizaremos con comentarios.
 */

@Repository
public interface ComentariosRepository extends JpaRepository<ComentarioModels, Integer> {

    @Query(value = "select * from comentarios where id_comentario = ?1 and id_usuario = (select id_usuario from usuarios where user = ?2)", nativeQuery = true)
    Optional<ComentarioModels> existByIdUser(int id, String user);

}
