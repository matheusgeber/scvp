package com.scvp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "reserva_passageiro", schema = "SCVP")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservaPassageiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva_passageiro")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reserva", nullable = false)
    private Reserva reserva;

    @Column(name = "nome_passageiro", nullable = false, length = 150)
    private String nomePassageiro;

    @Column(name = "cpf_passageiro", length = 11)
    private String cpfPassageiro;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "eh_titular")
    private Boolean ehTitular;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_responsavel_adulto")
    private ReservaPassageiro responsavelAdulto;

    @Column(name = "percentual_desconto_aplicado", precision = 5, scale = 2)
    private BigDecimal percentualDescontoAplicado;
}
