package com.scvp.service;

import com.scvp.model.Pagamento;
import com.scvp.model.Reserva;
import com.scvp.repository.PagamentoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final ReservaService reservaService;
    private final TicketService ticketService;

    public PagamentoService(PagamentoRepository pagamentoRepository,
                             ReservaService reservaService,
                             TicketService ticketService) {
        this.pagamentoRepository = pagamentoRepository;
        this.reservaService = reservaService;
        this.ticketService = ticketService;
    }

    public Pagamento processarPagamento(Long idReserva, String formaPagamento, boolean aprovado) {
        Reserva reserva = reservaService.buscarPorId(idReserva);

        Pagamento pagamento = new Pagamento();
        pagamento.setReserva(reserva);
        pagamento.setFormaPagamento(formaPagamento);
        pagamento.setValorPago(reserva.getValorTotal() != null ? reserva.getValorTotal() : BigDecimal.ZERO);
        pagamento.setDataPagamento(LocalDateTime.now());

        if (aprovado) {
            pagamento.setStatusTransacao("APROVADO");
            pagamentoRepository.save(pagamento);

            reservaService.confirmar(idReserva);
            ticketService.emitirTicket(idReserva);
        } else {
            pagamento.setStatusTransacao("NEGADO");
            pagamentoRepository.save(pagamento);
        }

        return pagamento;
    }
}