package com.proyectomarlon.app.crudrepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proyectomarlon.app.tables.Entrenador;

public interface EntrenadorRepositorio extends JpaRepository<Entrenador, Integer>{

	@Query("SELECT e FROM Entrenador e WHERE e.club IS NULL")
    List<Entrenador> findEntrenadoresSinClub();
}
