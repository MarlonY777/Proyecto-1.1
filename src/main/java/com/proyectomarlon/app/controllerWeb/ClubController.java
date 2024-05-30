package com.proyectomarlon.app.controllerWeb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import com.proyectomarlon.app.crudrepository.AsociacionRepositorio;
import com.proyectomarlon.app.crudrepository.ClubRepositorio;
import com.proyectomarlon.app.crudrepository.CompeticionRepositorio;
import com.proyectomarlon.app.crudrepository.EntrenadorRepositorio;
import com.proyectomarlon.app.tables.Asociacion;
import com.proyectomarlon.app.tables.Club;
import com.proyectomarlon.app.tables.Competicion;
import com.proyectomarlon.app.tables.Entrenador;

@Controller
public class ClubController {
	
	@Autowired
	private ClubRepositorio clubRepositorio;
	
	@Autowired
	private EntrenadorRepositorio entrenadorRepositorio;
	
	@Autowired
	private AsociacionRepositorio asociacionRepositorio;
	
	@Autowired
	private CompeticionRepositorio competicionRepositorio;
	
	@GetMapping({"/verClub"})  
	public String listarClub(Model model) {
		List<Club> listaClubes = clubRepositorio.findAll();
		model.addAttribute("listaClubes", listaClubes);
		
		return "verClub";
	}
	
	@GetMapping("/verClub/formClub")
	public String mostrarFormulario (Model model) {
		model.addAttribute("club", new Club());
		
		model.addAttribute("entrenadores", entrenadorRepositorio.findEntrenadoresSinClub());
		
		List<Asociacion> listaAsociacion = asociacionRepositorio.findAll();
		model.addAttribute("listaAsociacion", listaAsociacion);
		
		List<Competicion> listaCompeticion = competicionRepositorio.findAll();
		model.addAttribute("listaCompeticion", listaCompeticion);
		
		return "formClub";
	}
	
	@PostMapping("/guardarClub")
	public String guardarClub(Club club) {
	    clubRepositorio.save(club);
	    Entrenador entrenador = club.getEntrenador();
	    if (entrenador != null) {
	        entrenador.setClub(club);
	        entrenadorRepositorio.save(entrenador);
	    }

	    return "redirect:/verClub";
	}
	
	@GetMapping("/club/editar/{id}")
	public String modificarClub (@PathVariable("id") Integer id, Model model) {
		Club club = clubRepositorio.findById(id).get();
		model.addAttribute("club", club);
		
		model.addAttribute("entrenadores", entrenadorRepositorio.findEntrenadoresSinClub());

		
		List<Asociacion> listaAsociacion = asociacionRepositorio.findAll();
		model.addAttribute("listaAsociacion", listaAsociacion);
		
		List<Competicion> listaCompeticion = competicionRepositorio.findAll();
		model.addAttribute("listaCompeticion", listaCompeticion);
		
		return "formClub";
	}
	
	@GetMapping("/club/eliminar/{id}")
	public String eliminarClub(@PathVariable("id") Integer id, Model model) {
		clubRepositorio.deleteById(id);
		return "redirect:/verClub";
	}
	
}