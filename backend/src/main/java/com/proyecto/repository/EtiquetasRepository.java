package com.proyecto.repository;

import com.proyecto.models.EtiquetaModels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 *  Capa de Repositorio.
 *  Definimos las querys que utilizaremos con etiquetas.
 */

@Repository
public interface EtiquetasRepository extends JpaRepository<EtiquetaModels, Integer> {

    @Query(value = "select count(*) > 0 from etiquetas where etiqueta = ?1", nativeQuery = true)
    Integer verificarExisteEtiqueta(String etiqueta);

}
