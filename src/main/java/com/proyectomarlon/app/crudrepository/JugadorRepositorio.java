package com.proyectomarlon.app.crudrepository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.proyectomarlon.app.tables.Jugador;

public interface JugadorRepositorio  extends JpaRepository<Jugador, Integer>{
}
