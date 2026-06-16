package com.scvp.controller;

import com.scvp.model.Transportadora;
import com.scvp.service.TransportadoraService;

import org.springframework.dao.DataIntegrityViolationException;
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
        return "transportadora/listaTransportadora";
    }

    @GetMapping("/nova")
    public String form(Model model) {
    	if (!model.containsAttribute("transportadora")) {
            model.addAttribute("transportadora", new Transportadora());
        }
        return "transportadora/formTransportadora";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Transportadora transportadora, RedirectAttributes ra) {
    	try {
            transportadoraService.cadastrar(transportadora);
            ra.addFlashAttribute("sucesso", "Transportadora cadastrada com sucesso!");
            return "redirect:/transportadoras";
        } catch (IllegalArgumentException e) {
            ra.addFlashAttribute("erro", e.getMessage());
            ra.addFlashAttribute("transportadora", transportadora);
            return "redirect:/transportadoras/nova";
        } catch (DataIntegrityViolationException e) {
            ra.addFlashAttribute("erro", "CNPJ já cadastrado.");
            ra.addFlashAttribute("transportadora", transportadora);
            return "redirect:/transportadoras/nova";
        }
    }
}