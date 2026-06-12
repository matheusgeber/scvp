package com.scvp.model;

import java.math.BigDecimal;
import java.time.LocalDate;

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
@Table(name = "reserva_passageiro")
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

    public ReservaPassageiro() {}

    public ReservaPassageiro(Long id, Reserva reserva, String nomePassageiro, String cpfPassageiro,
                              LocalDate dataNascimento, Boolean ehTitular, ReservaPassageiro responsavelAdulto,
                              BigDecimal percentualDescontoAplicado) {
        this.id = id;
        this.reserva = reserva;
        this.nomePassageiro = nomePassageiro;
        this.cpfPassageiro = cpfPassageiro;
        this.dataNascimento = dataNascimento;
        this.ehTitular = ehTitular;
        this.responsavelAdulto = responsavelAdulto;
        this.percentualDescontoAplicado = percentualDescontoAplicado;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public String getNomePassageiro() {
		return nomePassageiro;
	}

	public void setNomePassageiro(String nomePassageiro) {
		this.nomePassageiro = nomePassageiro;
	}

	public String getCpfPassageiro() {
		return cpfPassageiro;
	}

	public void setCpfPassageiro(String cpfPassageiro) {
		this.cpfPassageiro = cpfPassageiro;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Boolean getEhTitular() {
		return ehTitular;
	}

	public void setEhTitular(Boolean ehTitular) {
		this.ehTitular = ehTitular;
	}

	public ReservaPassageiro getResponsavelAdulto() {
		return responsavelAdulto;
	}

	public void setResponsavelAdulto(ReservaPassageiro responsavelAdulto) {
		this.responsavelAdulto = responsavelAdulto;
	}

	public BigDecimal getPercentualDescontoAplicado() {
		return percentualDescontoAplicado;
	}

	public void setPercentualDescontoAplicado(BigDecimal percentualDescontoAplicado) {
		this.percentualDescontoAplicado = percentualDescontoAplicado;
	}
    
}
