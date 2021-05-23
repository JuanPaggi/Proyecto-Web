package com.proyecto.repository;


import com.proyecto.models.ComentarioModels;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;



@Repository
public interface ComentariosRepository extends JpaRepository<ComentarioModels, Integer> {


}
