package com.scvp.model;

import java.math.BigDecimal;
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
@Table(name = "pagamento", schema = "SCVP")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pagamento")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reserva", nullable = false)
    private Reserva reserva;

    @Column(name = "forma_pagamento", length = 20)
    private String formaPagamento;

    @Column(name = "status_transacao", length = 20)
    private String statusTransacao;

    @Column(name = "valor_pago", precision = 12, scale = 2)
    private BigDecimal valorPago;

    @Column(name = "data_pagamento")
    private LocalDateTime dataPagamento;

    @Column(name = "codigo_transacao_gateway", length = 100)
    private String codigoTransacaoGateway;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "confirmado_por_funcionario")
    private Funcionario confirmadoPorFuncionario;

    public Pagamento() {}

    public Pagamento(Long id, Reserva reserva, String formaPagamento, String statusTransacao,
                      BigDecimal valorPago, LocalDateTime dataPagamento, String codigoTransacaoGateway,
                      Funcionario confirmadoPorFuncionario) {
        this.id = id;
        this.reserva = reserva;
        this.formaPagamento = formaPagamento;
        this.statusTransacao = statusTransacao;
        this.valorPago = valorPago;
        this.dataPagamento = dataPagamento;
        this.codigoTransacaoGateway = codigoTransacaoGateway;
        this.confirmadoPorFuncionario = confirmadoPorFuncionario;
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

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public String getStatusTransacao() {
		return statusTransacao;
	}

	public void setStatusTransacao(String statusTransacao) {
		this.statusTransacao = statusTransacao;
	}

	public BigDecimal getValorPago() {
		return valorPago;
	}

	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}

	public LocalDateTime getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDateTime dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public String getCodigoTransacaoGateway() {
		return codigoTransacaoGateway;
	}

	public void setCodigoTransacaoGateway(String codigoTransacaoGateway) {
		this.codigoTransacaoGateway = codigoTransacaoGateway;
	}

	public Funcionario getConfirmadoPorFuncionario() {
		return confirmadoPorFuncionario;
	}

	public void setConfirmadoPorFuncionario(Funcionario confirmadoPorFuncionario) {
		this.confirmadoPorFuncionario = confirmadoPorFuncionario;
	}
    
}
