package com.scvp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "reserva", schema = "SCVP")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_viagem", nullable = false)
    private Viagem viagem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ponto_venda")
    private PontoVenda pontoVenda;

    @Column(name = "canal_venda", length = 20)
    private String canalVenda;

    @Column(name = "status_reserva", length = 20)
    private String statusReserva;

    @Column(name = "valor_bruto", precision = 12, scale = 2)
    private BigDecimal valorBruto;

    @Column(name = "valor_desconto", precision = 12, scale = 2)
    private BigDecimal valorDesconto;

    @Column(name = "valor_total", precision = 12, scale = 2)
    private BigDecimal valorTotal;

    @Column(name = "data_reserva")
    private LocalDateTime dataReserva;

    @Column(name = "data_confirmacao")
    private LocalDateTime dataConfirmacao;

    @Column(name = "data_cancelamento")
    private LocalDateTime dataCancelamento;

    @PrePersist
    protected void onCreate() {
        if (this.dataReserva == null) {
            this.dataReserva = LocalDateTime.now();
        }
    }
}
