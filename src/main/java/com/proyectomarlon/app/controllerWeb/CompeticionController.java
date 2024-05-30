package com.proyectomarlon.app.controllerWeb;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.proyectomarlon.app.crudrepository.CompeticionRepositorio;
import com.proyectomarlon.app.tables.Competicion;

@Controller
public class CompeticionController {

    @Autowired
    private CompeticionRepositorio competicionRepositorio;

    @GetMapping("/verCompeticion")
    public String listarCompeticiones(Model model) {
        List<Competicion> listaCompeticiones = competicionRepositorio.findAll();
        model.addAttribute("listaCompeticiones", listaCompeticiones);
        return "verCompeticion";
    }

    @GetMapping("/verCompeticion/formCompeticion")
    public String mostrarFormulario(Model model) {
        model.addAttribute("competicion", new Competicion());
        return "formCompeticion";
    }

    @PostMapping("/guardarCompeticion")
    public String guardarCompeticion(Competicion competicion) {
        competicionRepositorio.save(competicion);
        return "redirect:/verCompeticion";
    }

    @GetMapping("/competicion/editar/{id}")
    public String modificarCompeticion(@PathVariable("id") Integer id, Model model) {
        Competicion competicion = competicionRepositorio.findById(id).orElse(null);
        if (competicion != null) {
            model.addAttribute("competicion", competicion);
            return "formCompeticion";
        } else {
            return "redirect:/verCompeticion";
        }
    }

    @GetMapping("/competicion/eliminar/{id}")
    public String eliminarCompeticion(@PathVariable("id") Integer id) {
        competicionRepositorio.deleteById(id);
        return "redirect:/verCompeticion";
    }
}
