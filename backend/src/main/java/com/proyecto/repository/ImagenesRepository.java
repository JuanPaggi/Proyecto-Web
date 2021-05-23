package com.proyecto.repository;

import com.proyecto.models.ImagenModels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImagenesRepository extends JpaRepository<ImagenModels, Integer> {

    @Query(value = "select * from imagenes where imagen_hash = ?1", nativeQuery = true)
    Optional<ImagenModels> findByHash(byte[] img);

}
