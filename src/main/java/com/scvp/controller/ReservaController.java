package com.scvp.controller;

import com.scvp.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;

@Controller
@RequestMapping("/reservas")
public class ReservaController {

    private final ReservaService reservaService;
    private final ClienteService clienteService;
    private final ViagemService viagemService;

    public ReservaController(ReservaService reservaService,
                              ClienteService clienteService,
                              ViagemService viagemService) {
        this.reservaService = reservaService;
        this.clienteService = clienteService;
        this.viagemService = viagemService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("reservas", reservaService.listarTodos());
        return "reserva/listaReserva";
    }

    @GetMapping("/nova")
    public String form(Model model) {
        model.addAttribute("clientes", clienteService.listarTodos());
        model.addAttribute("viagens", viagemService.listarTodos());
        return "reserva/formReserva";
    }

    @PostMapping("/salvar")
    public String salvar(@RequestParam Long idCliente, @RequestParam Long idViagem, @RequestParam int quantidadePassageiros, @RequestParam BigDecimal valorBruto,
                          RedirectAttributes ra) {

        var cliente = clienteService.buscarPorId(idCliente);
        var reserva = reservaService.criarReserva(cliente, idViagem, quantidadePassageiros, valorBruto);

        ra.addFlashAttribute("sucesso", "Reserva #" + reserva.getId() + " criada com sucesso!");
        return "redirect:/reservas";
    }

    @PostMapping("/cancelar/{id}")
    public String cancelar(@PathVariable Long id, RedirectAttributes ra) {
        reservaService.cancelar(id);
        ra.addFlashAttribute("sucesso", "Reserva cancelada com sucesso!");
        return "redirect:/reservas";
    }
}