package com.scvp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "parcelamento_pagamento", schema = "SCVP")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParcelamentoPagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_parcela")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pagamento", nullable = false)
    private Pagamento pagamento;

    @Column(name = "numero_parcela")
    private Integer numeroParcela;

    @Column(name = "valor_parcela", precision = 12, scale = 2)
    private BigDecimal valorParcela;

    @Column(name = "juros_percentual", precision = 5, scale = 2)
    private BigDecimal jurosPercentual;

    @Column(name = "data_vencimento")
    private LocalDate dataVencimento;
}
