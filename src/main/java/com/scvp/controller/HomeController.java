package com.scvp.controller;

import com.scvp.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final ClienteRepository clienteRepository;
    private final ViagemRepository viagemRepository;
    private final ReservaRepository reservaRepository;
    private final TicketRepository ticketRepository;

    public HomeController(ClienteRepository clienteRepository,
                           ViagemRepository viagemRepository,
                           ReservaRepository reservaRepository,
                           TicketRepository ticketRepository) {
        this.clienteRepository = clienteRepository;
        this.viagemRepository = viagemRepository;
        this.reservaRepository = reservaRepository;
        this.ticketRepository = ticketRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("totalClientes", clienteRepository.count());
        model.addAttribute("totalViagens", viagemRepository.count());
        model.addAttribute("totalReservas", reservaRepository.count());
        model.addAttribute("totalTickets", ticketRepository.count());
        return "index";
    }
}