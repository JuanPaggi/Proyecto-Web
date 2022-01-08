package com.proyecto.repository;

import com.proyecto.models.GaleriaModels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GaleriasRepository extends JpaRepository<GaleriaModels, Integer>  {

    @Query(value = "select * from galerias", nativeQuery = true)
    List<GaleriaModels> getAll();

}
