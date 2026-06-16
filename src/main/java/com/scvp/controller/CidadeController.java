package com.scvp.controller;

import com.scvp.model.Cidade;
import com.scvp.service.CidadeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cidades")
public class CidadeController {

    private final CidadeService cidadeService;

    public CidadeController(CidadeService cidadeService) {
        this.cidadeService = cidadeService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("cidades", cidadeService.listarTodos());
        return "cidade/listaCidade";
    }

    @GetMapping("/nova")
    public String form() {
        return "cidade/formCidade";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Cidade cidade, RedirectAttributes ra) {
        cidadeService.cadastrar(cidade);
        ra.addFlashAttribute("sucesso", "Cidade cadastrada com sucesso!");
        return "redirect:/cidades";
    }
}