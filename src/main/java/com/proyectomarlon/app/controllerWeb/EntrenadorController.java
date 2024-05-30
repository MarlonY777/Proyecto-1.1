package com.proyectomarlon.app.controllerWeb;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.proyectomarlon.app.crudrepository.ClubRepositorio;
import com.proyectomarlon.app.crudrepository.EntrenadorRepositorio;
import com.proyectomarlon.app.tables.Club;
import com.proyectomarlon.app.tables.Entrenador;

@Controller
public class EntrenadorController {

    @Autowired
    private EntrenadorRepositorio entrenadorRepositorio;
    
    @Autowired
    private ClubRepositorio clubRepositorio;

    @GetMapping("/verEntrenador")
    public String listarEntrenadores(Model model) {
        List<Entrenador> listaEntrenadores = entrenadorRepositorio.findAll();
        model.addAttribute("listaEntrenadores", listaEntrenadores);
        return "verEntrenador";
    }

    @GetMapping("/verEntrenador/formEntrenador")
    public String mostrarFormulario(Model model) {
        model.addAttribute("entrenador", new Entrenador());
        
        List<Club> listaClubes = clubRepositorio.findAll();
        model.addAttribute("listaClubes", listaClubes);
        
        return "formEntrenador";
    }

    @PostMapping("/guardarEntrenador")
    public String guardarEntrenador(Entrenador entrenador) {
        entrenadorRepositorio.save(entrenador);
        return "redirect:/verEntrenador";
    }

    @GetMapping("/entrenador/editar/{id}")
    public String modificarEntrenador(@PathVariable("id") Integer id, Model model) {
        Entrenador entrenador = entrenadorRepositorio.findById(id).orElse(null);
        if (entrenador != null) {
            model.addAttribute("entrenador", entrenador);
            return "formEntrenador";
        } else {
            return "redirect:/verEntrenador";
        }
    }

    @GetMapping("/entrenador/eliminar/{id}")
    public String eliminarEntrenador(@PathVariable("id") Integer id) {
        entrenadorRepositorio.deleteById(id);
        return "redirect:/verEntrenador";
    }
}
