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
@Table(name = "viagem_escala", schema = "SCVP")
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

    public ViagemEscala() {}

    public ViagemEscala(Long id, Viagem viagem, Integer ordemEscala, Cidade cidadeEscala,
                         Aeroporto aeroportoEscala, LocalDateTime chegadaPrevista, LocalDateTime saidaPrevista) {
        this.id = id;
        this.viagem = viagem;
        this.ordemEscala = ordemEscala;
        this.cidadeEscala = cidadeEscala;
        this.aeroportoEscala = aeroportoEscala;
        this.chegadaPrevista = chegadaPrevista;
        this.saidaPrevista = saidaPrevista;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Viagem getViagem() {
		return viagem;
	}

	public void setViagem(Viagem viagem) {
		this.viagem = viagem;
	}

	public Integer getOrdemEscala() {
		return ordemEscala;
	}

	public void setOrdemEscala(Integer ordemEscala) {
		this.ordemEscala = ordemEscala;
	}

	public Cidade getCidadeEscala() {
		return cidadeEscala;
	}

	public void setCidadeEscala(Cidade cidadeEscala) {
		this.cidadeEscala = cidadeEscala;
	}

	public Aeroporto getAeroportoEscala() {
		return aeroportoEscala;
	}

	public void setAeroportoEscala(Aeroporto aeroportoEscala) {
		this.aeroportoEscala = aeroportoEscala;
	}

	public LocalDateTime getChegadaPrevista() {
		return chegadaPrevista;
	}

	public void setChegadaPrevista(LocalDateTime chegadaPrevista) {
		this.chegadaPrevista = chegadaPrevista;
	}

	public LocalDateTime getSaidaPrevista() {
		return saidaPrevista;
	}

	public void setSaidaPrevista(LocalDateTime saidaPrevista) {
		this.saidaPrevista = saidaPrevista;
	}
    
}