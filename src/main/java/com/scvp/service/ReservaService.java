package com.scvp.service;

import com.scvp.model.*;
import com.scvp.repository.ReservaRepository;
import com.scvp.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final ViagemService viagemService;
    private final TicketRepository ticketRepository;

    public ReservaService(ReservaRepository reservaRepository, ViagemService viagemService, TicketRepository ticketRepository) {
        this.reservaRepository = reservaRepository;
        this.viagemService = viagemService;
        this.ticketRepository = ticketRepository;
    }

    public Reserva criarReserva(Cliente cliente, Long idViagem, int quantidadePassageiros, BigDecimal valorBruto) {
        Viagem viagem = viagemService.buscarPorId(idViagem);

        verificarCapacidade(viagem, quantidadePassageiros);

        Reserva reserva = new Reserva();
        reserva.setCliente(cliente);
        reserva.setViagem(viagem);
        reserva.setCanalVenda("PRESENCIAL");
        reserva.setStatusReserva("PENDENTE");
        reserva.setValorBruto(valorBruto);
        reserva.setValorDesconto(BigDecimal.ZERO);
        reserva.setValorTotal(valorBruto);

        return reservaRepository.save(reserva);
    }

    private void verificarCapacidade(Viagem viagem, int quantidadePassageiros) {
        Modal modal = viagem.getModal();
        int capacidadeTotal = modal.getCapacidadeTotal();

        long ocupados = reservaRepository.findByViagemId(viagem.getId()).stream().filter(r -> !"CANCELADA".equals(r.getStatusReserva())).count();

        if (ocupados + quantidadePassageiros > capacidadeTotal) {
            throw new IllegalStateException("Sem vaga disponível para esta viagem(capacidade excedida).");
        }
    }

    public Reserva cancelar(Long idReserva) {
        Reserva reserva = buscarPorId(idReserva);

        boolean ticketEmitido = ticketRepository.findByReservaId(idReserva).isPresent();
        if (ticketEmitido) {
            throw new IllegalStateException("Cancelamento não permitido: ticket já foi emitido.");
        }

        reserva.setStatusReserva("CANCELADA");
        reserva.setDataCancelamento(LocalDateTime.now());
        return reservaRepository.save(reserva);
    }

    public Reserva confirmar(Long idReserva) {
        Reserva reserva = buscarPorId(idReserva);
        reserva.setStatusReserva("CONFIRMADA");
        reserva.setDataConfirmacao(LocalDateTime.now());
        return reservaRepository.save(reserva);
    }

    public Reserva buscarPorId(Long id) {
        return reservaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Reserva não encontrada."));
    }

    public List<Reserva> listarTodos() {
        return reservaRepository.findAll();
    }
}