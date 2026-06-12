package com.scvp.model;

import java.time.LocalDateTime;

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
@Table(name = "viagem", schema = "SCVP")
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

    public Viagem() {}

    public Viagem(Long id, Modal modal, Cidade origemCidade, Cidade destinoCidade,
                   Aeroporto origemAeroporto, Aeroporto destinoAeroporto,
                   LocalDateTime dataHoraSaida, LocalDateTime dataHoraChegada, String statusViagem) {
        this.id = id;
        this.modal = modal;
        this.origemCidade = origemCidade;
        this.destinoCidade = destinoCidade;
        this.origemAeroporto = origemAeroporto;
        this.destinoAeroporto = destinoAeroporto;
        this.dataHoraSaida = dataHoraSaida;
        this.dataHoraChegada = dataHoraChegada;
        this.statusViagem = statusViagem;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Modal getModal() {
		return modal;
	}

	public void setModal(Modal modal) {
		this.modal = modal;
	}

	public Cidade getOrigemCidade() {
		return origemCidade;
	}

	public void setOrigemCidade(Cidade origemCidade) {
		this.origemCidade = origemCidade;
	}

	public Cidade getDestinoCidade() {
		return destinoCidade;
	}

	public void setDestinoCidade(Cidade destinoCidade) {
		this.destinoCidade = destinoCidade;
	}

	public Aeroporto getOrigemAeroporto() {
		return origemAeroporto;
	}

	public void setOrigemAeroporto(Aeroporto origemAeroporto) {
		this.origemAeroporto = origemAeroporto;
	}

	public Aeroporto getDestinoAeroporto() {
		return destinoAeroporto;
	}

	public void setDestinoAeroporto(Aeroporto destinoAeroporto) {
		this.destinoAeroporto = destinoAeroporto;
	}

	public LocalDateTime getDataHoraSaida() {
		return dataHoraSaida;
	}

	public void setDataHoraSaida(LocalDateTime dataHoraSaida) {
		this.dataHoraSaida = dataHoraSaida;
	}

	public LocalDateTime getDataHoraChegada() {
		return dataHoraChegada;
	}

	public void setDataHoraChegada(LocalDateTime dataHoraChegada) {
		this.dataHoraChegada = dataHoraChegada;
	}

	public String getStatusViagem() {
		return statusViagem;
	}

	public void setStatusViagem(String statusViagem) {
		this.statusViagem = statusViagem;
	}
        
}
