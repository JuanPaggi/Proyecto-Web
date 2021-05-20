package com.proyecto.repository;

import com.proyecto.models.UsuarioModels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuariosRepository extends JpaRepository<UsuarioModels, Integer> {
}
