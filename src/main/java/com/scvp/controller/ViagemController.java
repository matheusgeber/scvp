package com.scvp.controller;

import com.scvp.model.Viagem;
import com.scvp.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        return "viagem/lista";
    }

    @GetMapping("/nova")
    public String form(Model model) {
        model.addAttribute("viagem", new Viagem());
        model.addAttribute("modais", modalService.listarTodos());
        model.addAttribute("cidades", cidadeService.listarTodos());
        model.addAttribute("aeroportos", aeroportoService.listarTodos());
        return "viagem/form";
    }

    @PostMapping("/salvar")
    public String salvar(@RequestParam Long idModal,
                          @RequestParam Long idOrigemCidade,
                          @RequestParam Long idDestinoCidade,
                          @RequestParam Long idOrigemAeroporto,
                          @RequestParam Long idDestinoAeroporto,
                          @RequestParam String dataHoraSaida,
                          @RequestParam String dataHoraChegada,
                          RedirectAttributes ra) {

        viagemService.cadastrarComIds(idModal, idOrigemCidade, idDestinoCidade,
                idOrigemAeroporto, idDestinoAeroporto, dataHoraSaida, dataHoraChegada);

        ra.addFlashAttribute("sucesso", "Viagem cadastrada com sucesso!");
        return "redirect:/viagens";
    }
}