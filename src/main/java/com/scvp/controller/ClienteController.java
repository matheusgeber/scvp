package com.scvp.controller;

import com.scvp.model.Cliente;
import com.scvp.service.ClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("clientes", clienteService.listarTodos());
        return "cliente/lista";
    }

    @GetMapping("/novo")
    public String form(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "cliente/form";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Cliente cliente, RedirectAttributes ra) {
        clienteService.cadastrar(cliente);
        ra.addFlashAttribute("sucesso", "Cliente cadastrado com sucesso!");
        return "redirect:/clientes";
    }
}