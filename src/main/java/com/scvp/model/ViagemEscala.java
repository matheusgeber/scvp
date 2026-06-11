package com.scvp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "viagem_escala", schema = "SCVP")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ViagemEscala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_escala")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_viagem", nullable = false)
    private Viagem viagem;

    @Column(name = "ordem_escala")
    private Integer ordemEscala;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cidade_escala")
    private Cidade cidadeEscala;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_aeroporto_escala")
    private Aeroporto aeroportoEscala;

    @Column(name = "chegada_prevista")
    private LocalDateTime chegadaPrevista;

    @Column(name = "saida_prevista")
    private LocalDateTime saidaPrevista;
}