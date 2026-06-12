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
@Table(name = "parcelamento_pagamento", schema = "SCVP")
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

    public ParcelamentoPagamento() {}

    public ParcelamentoPagamento(Long id, Pagamento pagamento, Integer numeroParcela, BigDecimal valorParcela,
                                  BigDecimal jurosPercentual, LocalDate dataVencimento) {
        this.id = id;
        this.pagamento = pagamento;
        this.numeroParcela = numeroParcela;
        this.valorParcela = valorParcela;
        this.jurosPercentual = jurosPercentual;
        this.dataVencimento = dataVencimento;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Integer getNumeroParcela() {
		return numeroParcela;
	}

	public void setNumeroParcela(Integer numeroParcela) {
		this.numeroParcela = numeroParcela;
	}

	public BigDecimal getValorParcela() {
		return valorParcela;
	}

	public void setValorParcela(BigDecimal valorParcela) {
		this.valorParcela = valorParcela;
	}

	public BigDecimal getJurosPercentual() {
		return jurosPercentual;
	}

	public void setJurosPercentual(BigDecimal jurosPercentual) {
		this.jurosPercentual = jurosPercentual;
	}

	public LocalDate getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
    
}
