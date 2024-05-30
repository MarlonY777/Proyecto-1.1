package com.proyectomarlon.app.crudrepository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.proyectomarlon.app.tables.Competicion;

public interface CompeticionRepositorio extends JpaRepository <Competicion, Integer>{
}
