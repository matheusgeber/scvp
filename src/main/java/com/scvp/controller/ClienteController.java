package com.scvp.controller;

import com.scvp.model.Cliente;
import com.scvp.service.ClienteService;

import org.springframework.dao.DataIntegrityViolationException;
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
        return "cliente/listaCliente";
    }

    @GetMapping("/novo")
    public String form(Model model) {
        if (!model.containsAttribute("cliente")) {
            model.addAttribute("cliente", new Cliente());
        }
        return "cliente/formCliente";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Cliente cliente, RedirectAttributes ra) {
        try {
            clienteService.cadastrar(cliente);
            ra.addFlashAttribute("sucesso", "Cliente cadastrado com sucesso!");
            return "redirect:/clientes";
        } catch (IllegalArgumentException e) {
            ra.addFlashAttribute("erro", e.getMessage());
            ra.addFlashAttribute("cliente", cliente);
            return "redirect:/clientes/novo";
        } catch (DataIntegrityViolationException e) {
            String msg = extrairMensagem(e);
            ra.addFlashAttribute("erro", msg);
            ra.addFlashAttribute("cliente", cliente);
            return "redirect:/clientes/novo";
        }
    }
    
    private String extrairMensagem(DataIntegrityViolationException e) {
        String msg = e.getMessage() != null ? e.getMessage() : "";
        if (msg.contains("cpf")) return "CPF já cadastrado.";
        if (msg.contains("email")) return "E-mail já cadastrado.";
        return "Dado duplicado — verifique os campos únicos (CPF, e-mail).";
    }
}