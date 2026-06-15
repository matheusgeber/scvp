package com.scvp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.scvp.service.AeroportoService;
import com.scvp.service.CidadeService;
import com.scvp.service.ModalService;
import com.scvp.service.ViagemService;

@Controller
@RequestMapping("/viagens")
public class ViagemController {

    private final ViagemService viagemService;
    private final ModalService modalService;
    private final CidadeService cidadeService;
    private final AeroportoService aeroportoService;

    public ViagemController(ViagemService viagemService, ModalService modalService,
                             CidadeService cidadeService, AeroportoService aeroportoService) {
        this.viagemService = viagemService;
        this.modalService = modalService;
        this.cidadeService = cidadeService;
        this.aeroportoService = aeroportoService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("viagens", viagemService.listarTodos());
        return "viagem/listaViagem";
    }

    @GetMapping("/nova")
    public String form(Model model) {
        model.addAttribute("modais", modalService.listarTodos());
        model.addAttribute("cidades", cidadeService.listarTodos());
        if (!model.containsAttribute("idModal")) {
            model.addAttribute("idModal", "");
            model.addAttribute("idOrigemCidade", "");
            model.addAttribute("idDestinoCidade", "");
            model.addAttribute("dataHoraSaida", "");
        }
        return "viagem/formViagem";
    }

    @PostMapping("/salvar")
    public String salvar(@RequestParam Long idModal, @RequestParam Long idOrigemCidade, @RequestParam Long idDestinoCidade, @RequestParam String dataHoraSaida,
                          RedirectAttributes ra) {
        try {
            viagemService.cadastrarComIds(idModal, idOrigemCidade, idDestinoCidade, null, null, dataHoraSaida, null);
            ra.addFlashAttribute("sucesso", "Viagem cadastrada com sucesso!");
            return "redirect:/viagens";

        } catch (IllegalArgumentException | IllegalStateException e) {
            ra.addFlashAttribute("erro", e.getMessage());
            ra.addFlashAttribute("idModal", idModal);
            ra.addFlashAttribute("idOrigemCidade", idOrigemCidade);
            ra.addFlashAttribute("idDestinoCidade", idDestinoCidade);
            ra.addFlashAttribute("dataHoraSaida", dataHoraSaida);
            return "redirect:/viagens/nova";
        }
    }
}