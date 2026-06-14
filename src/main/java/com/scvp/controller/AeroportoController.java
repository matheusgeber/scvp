package com.scvp.controller;

import com.scvp.model.Aeroporto;
import com.scvp.service.AeroportoService;
import com.scvp.service.CidadeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/aeroportos")
public class AeroportoController {

    private final AeroportoService aeroportoService;
    private final CidadeService cidadeService;

    public AeroportoController(AeroportoService aeroportoService, CidadeService cidadeService) {
        this.aeroportoService = aeroportoService;
        this.cidadeService = cidadeService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("aeroportos", aeroportoService.listarTodos());
        return "aeroporto/lista";
    }

    @GetMapping("/novo")
    public String form(Model model) {
        model.addAttribute("cidades", cidadeService.listarTodos());
        return "aeroporto/form";
    }

    @PostMapping("/salvar")
    public String salvar(@RequestParam Long idCidade,
                          @RequestParam String nome,
                          @RequestParam String codigoIata,
                          RedirectAttributes ra) {

        var cidade = cidadeService.buscarPorId(idCidade);

        Aeroporto aeroporto = new Aeroporto();
        aeroporto.setCidade(cidade);
        aeroporto.setNome(nome);
        aeroporto.setCodigoIata(codigoIata);

        aeroportoService.cadastrar(aeroporto);
        ra.addFlashAttribute("sucesso", "Aeroporto cadastrado com sucesso!");
        return "redirect:/aeroportos";
    }
}