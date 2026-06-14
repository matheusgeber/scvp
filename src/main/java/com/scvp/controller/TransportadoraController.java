package com.scvp.controller;

import com.scvp.model.Transportadora;
import com.scvp.service.TransportadoraService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/transportadoras")
public class TransportadoraController {

    private final TransportadoraService transportadoraService;

    public TransportadoraController(TransportadoraService transportadoraService) {
        this.transportadoraService = transportadoraService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("transportadoras", transportadoraService.listarTodos());
        return "transportadora/lista";
    }

    @GetMapping("/nova")
    public String form() {
        return "transportadora/form";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Transportadora transportadora, RedirectAttributes ra) {
        transportadoraService.cadastrar(transportadora);
        ra.addFlashAttribute("sucesso", "Transportadora cadastrada com sucesso!");
        return "redirect:/transportadoras";
    }
}