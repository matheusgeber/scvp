package com.scvp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "funcionario_ponto_venda", schema = "SCVP")
@IdClass(FuncionarioPontoVendaId.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioPontoVenda {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_funcionario")
    private Funcionario funcionario;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ponto_venda")
    private PontoVenda pontoVenda;

    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @Column(name = "data_fim")
    private LocalDate dataFim;

    @Column(name = "autorizado_por_gerente")
    private Boolean autorizadoPorGerente;
}
