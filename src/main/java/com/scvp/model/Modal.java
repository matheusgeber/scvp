package com.scvp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "modal", schema = "SCVP")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Modal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_modal")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_transportadora", nullable = false)
    private Transportadora transportadora;

    @Column(name = "tipo_modal", nullable = false, length = 20)
    private String tipoModal;

    @Column(name = "identificacao", length = 80)
    private String identificacao;

    @Column(name = "capacidade_total")
    private Integer capacidadeTotal;

    @Column(name = "estado_operacional", length = 20)
    private String estadoOperacional;
}
