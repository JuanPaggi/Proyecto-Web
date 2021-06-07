package com.proyecto.repository;

import com.proyecto.models.GaleriaModels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GaleriasRepository extends JpaRepository<GaleriaModels, Integer>  {



}
