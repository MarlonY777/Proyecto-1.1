package com.proyectomarlon.app.tables;
import jakarta.persistence.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name="clubes")
public class Club {
	
	@Id
	@Column(name="id")
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private int Id;
	
	@NotNull
	private String Nombre;
	
	@OneToOne(mappedBy = "club", cascade = CascadeType.ALL)
    private Entrenador entrenador;

	@OneToMany
	@JoinColumn(name = "id_club")
	private List<Jugador> jugadores;
	
	@ManyToOne
	private Asociacion asociacion;
	
	@ManyToMany
	private List<Competicion> competiciones;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public Entrenador getEntrenador() {
		return entrenador;
	}

	public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
        if (entrenador != null) {
            entrenador.setClub(this);
        }
    }

	public List<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(List<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

	public Asociacion getAsociacion() {
		return asociacion;
	}

	public void setAsociacion(Asociacion asociacion) {
		this.asociacion = asociacion;
	}

	public List<Competicion> getCompeticiones() {
		return competiciones;
	}

	public void setCompeticiones(List<Competicion> competiciones) {
		this.competiciones = competiciones;
	}
}
