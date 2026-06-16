package com.scvp.controller;

import com.scvp.service.PagamentoService;
import com.scvp.service.ReservaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/pagamentos")
public class PagamentoController {

    private final PagamentoService pagamentoService;
    private final ReservaService reservaService;

    public PagamentoController(PagamentoService pagamentoService, ReservaService reservaService) {
        this.pagamentoService = pagamentoService;
        this.reservaService = reservaService;
    }

    @GetMapping("/novo/{idReserva}")
    public String form(@PathVariable Long idReserva, Model model) {
        model.addAttribute("reserva", reservaService.buscarPorId(idReserva));
        return "pagamento/formPagamento";
    }

    @PostMapping("/processar")
    public String processar(@RequestParam Long idReserva, @RequestParam String formaPagamento,
                             @RequestParam(defaultValue = "true") boolean aprovado,
                             RedirectAttributes ra) {

        var pagamento = pagamentoService.processarPagamento(idReserva, formaPagamento, aprovado);
        ra.addFlashAttribute("sucesso",
                "Pagamento " + pagamento.getStatusTransacao() + "! " + (aprovado ? "Reserva confirmada e ticket emitido!" : "Reserva permanece pendente."));
        return "redirect:/reservas";
    }
}