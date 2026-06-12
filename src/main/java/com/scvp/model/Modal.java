package com.scvp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "modal")
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

    public Modal() {}

    public Modal(Long id, Transportadora transportadora, String tipoModal, String identificacao,
                  Integer capacidadeTotal, String estadoOperacional) {
        this.id = id;
        this.transportadora = transportadora;
        this.tipoModal = tipoModal;
        this.identificacao = identificacao;
        this.capacidadeTotal = capacidadeTotal;
        this.estadoOperacional = estadoOperacional;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Transportadora getTransportadora() {
		return transportadora;
	}

	public void setTransportadora(Transportadora transportadora) {
		this.transportadora = transportadora;
	}

	public String getTipoModal() {
		return tipoModal;
	}

	public void setTipoModal(String tipoModal) {
		this.tipoModal = tipoModal;
	}

	public String getIdentificacao() {
		return identificacao;
	}

	public void setIdentificacao(String identificacao) {
		this.identificacao = identificacao;
	}

	public Integer getCapacidadeTotal() {
		return capacidadeTotal;
	}

	public void setCapacidadeTotal(Integer capacidadeTotal) {
		this.capacidadeTotal = capacidadeTotal;
	}

	public String getEstadoOperacional() {
		return estadoOperacional;
	}

	public void setEstadoOperacional(String estadoOperacional) {
		this.estadoOperacional = estadoOperacional;
	}    
    
}
