package com.scvp.service;

import com.scvp.model.Reserva;
import com.scvp.model.Ticket;
import com.scvp.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final ReservaService reservaService;

    public TicketService(TicketRepository ticketRepository, ReservaService reservaService) {
        this.ticketRepository = ticketRepository;
        this.reservaService = reservaService;
    }

    public Ticket emitirTicket(Long idReserva) {
        Reserva reserva = reservaService.buscarPorId(idReserva);

        if (!"CONFIRMADA".equals(reserva.getStatusReserva())) {
            throw new IllegalStateException("Não é possível emitir ticket: reserva não confirmada.");
        }

        Ticket ticket = new Ticket();
        ticket.setReserva(reserva);
        ticket.setCodigoTicket(gerarCodigoTicket());
        ticket.setDataEmissao(LocalDateTime.now());
        ticket.setStatusTicket("EMITIDO");

        return ticketRepository.save(ticket);
    }

    private String gerarCodigoTicket() {
        return "VVV-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}