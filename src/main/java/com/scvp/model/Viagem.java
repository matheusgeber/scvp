package com.scvp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "viagem", schema = "SCVP")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Viagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_viagem")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_modal", nullable = false)
    private Modal modal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_origem_cidade")
    private Cidade origemCidade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_destino_cidade")
    private Cidade destinoCidade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_origem_aeroporto")
    private Aeroporto origemAeroporto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_destino_aeroporto")
    private Aeroporto destinoAeroporto;

    @Column(name = "data_hora_saida", nullable = false)
    private LocalDateTime dataHoraSaida;

    @Column(name = "data_hora_chegada")
    private LocalDateTime dataHoraChegada;

    @Column(name = "status_viagem", length = 20)
    private String statusViagem;
}
