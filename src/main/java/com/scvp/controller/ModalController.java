package com.scvp.controller;

import com.scvp.model.Modal;
import com.scvp.service.ModalService;
import com.scvp.service.TransportadoraService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/modais")
public class ModalController {

    private final ModalService modalService;
    private final TransportadoraService transportadoraService;

    public ModalController(ModalService modalService, TransportadoraService transportadoraService) {
        this.modalService = modalService;
        this.transportadoraService = transportadoraService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("modais", modalService.listarTodos());
        return "modal/lista";
    }

    @GetMapping("/novo")
    public String form(Model model) {
        model.addAttribute("transportadoras", transportadoraService.listarTodos());
        return "modal/form";
    }

    @PostMapping("/salvar")
    public String salvar(@RequestParam Long idTransportadora,
                          @RequestParam String tipoModal,
                          @RequestParam String identificacao,
                          @RequestParam Integer capacidadeTotal,
                          RedirectAttributes ra) {

        var transportadora = transportadoraService.buscarPorId(idTransportadora);

        Modal modal = new Modal();
        modal.setTransportadora(transportadora);
        modal.setTipoModal(tipoModal);
        modal.setIdentificacao(identificacao);
        modal.setCapacidadeTotal(capacidadeTotal);
        modal.setEstadoOperacional("OPERACIONAL");

        modalService.cadastrar(modal);
        ra.addFlashAttribute("sucesso", "Modal cadastrado com sucesso!");
        return "redirect:/modais";
    }
}