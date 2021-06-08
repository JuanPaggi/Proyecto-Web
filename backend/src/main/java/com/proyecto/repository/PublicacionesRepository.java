package com.proyecto.repository;

import com.proyecto.models.PublicacionModels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Capa de Repositorio.
 * Definimos las querys que utilizaremos con publicaciones.
 */

@Repository
public interface PublicacionesRepository extends JpaRepository<PublicacionModels, Integer> {

    @Query(value = "select * from publicaciones where id_publicacion= ?1", nativeQuery = true)
    Optional<PublicacionModels> obtenerPublicacion(int idPublicacion);

    @Query(value = "select * from publicaciones where id_publicacion = ?1 and id_usuario = (select id_usuario from usuarios where user = ?2)", nativeQuery = true)
    Optional<PublicacionModels> existByIdUser(int id, String user);

}
