package com.practica01.controller;

import com.practica01.entity.Estado;
import com.practica01.service.IEstadoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EstadoController {
    
    @Autowired
    private IEstadoService estadoService;
    
    @GetMapping("/estado")
    public String index(Model model) {
        List<Estado> listaEstado = estadoService.getAllEstado();
        model.addAttribute("titulo", "Tabla estados");
        model.addAttribute("Estado", listaEstado);
        return "estados";
       
    }
        
    @GetMapping("/estadoN")
    public String crearPersona(Model model) {
        List<Estado> listaEstado = estadoService.getAllEstado();
        model.addAttribute("estado", new Estado());
        model.addAttribute("estados", listaEstado);
        return "crear";
    }
    
    @PostMapping("/save")
    public String guardarEstado(@ModelAttribute Estado estado) {
        estadoService.saveEstado(estado);
        return "redirect:/estado";
    }
    @GetMapping("/editEstado/{id}")
    public String editarEstado(@PathVariable("id") Long idEstado, Model model) {
        Estado estado = estadoService.getEstadoById(idEstado);
        List<Estado> listaEstados= estadoService.getAllEstado();
        model.addAttribute("estado", estado);
        model.addAttribute("estados", listaEstados);
        return "crear";
    }
    @GetMapping("/delete/{id}")
    public String eliminarEstado(@PathVariable("id") Long idEstado) {
        estadoService.delete(idEstado);
        return "redirect:/estado";
    }
}
