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
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "reserva", schema = "SCVP")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_viagem", nullable = false)
    private Viagem viagem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ponto_venda")
    private PontoVenda pontoVenda;

    @Column(name = "canal_venda", length = 20)
    private String canalVenda;

    @Column(name = "status_reserva", length = 20)
    private String statusReserva;

    @Column(name = "valor_bruto", precision = 12, scale = 2)
    private BigDecimal valorBruto;

    @Column(name = "valor_desconto", precision = 12, scale = 2)
    private BigDecimal valorDesconto;

    @Column(name = "valor_total", precision = 12, scale = 2)
    private BigDecimal valorTotal;

    @Column(name = "data_reserva")
    private LocalDateTime dataReserva;

    @Column(name = "data_confirmacao")
    private LocalDateTime dataConfirmacao;

    @Column(name = "data_cancelamento")
    private LocalDateTime dataCancelamento;

    public Reserva() {}

    public Reserva(Long id, Cliente cliente, Viagem viagem, PontoVenda pontoVenda, String canalVenda,
                    String statusReserva, BigDecimal valorBruto, BigDecimal valorDesconto, BigDecimal valorTotal,
                    LocalDateTime dataReserva, LocalDateTime dataConfirmacao, LocalDateTime dataCancelamento) {
        this.id = id;
        this.cliente = cliente;
        this.viagem = viagem;
        this.pontoVenda = pontoVenda;
        this.canalVenda = canalVenda;
        this.statusReserva = statusReserva;
        this.valorBruto = valorBruto;
        this.valorDesconto = valorDesconto;
        this.valorTotal = valorTotal;
        this.dataReserva = dataReserva;
        this.dataConfirmacao = dataConfirmacao;
        this.dataCancelamento = dataCancelamento;
    }

    @PrePersist
    protected void onCreate() {
        if (this.dataReserva == null) {
            this.dataReserva = LocalDateTime.now();
        }
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Viagem getViagem() {
		return viagem;
	}

	public void setViagem(Viagem viagem) {
		this.viagem = viagem;
	}

	public PontoVenda getPontoVenda() {
		return pontoVenda;
	}

	public void setPontoVenda(PontoVenda pontoVenda) {
		this.pontoVenda = pontoVenda;
	}

	public String getCanalVenda() {
		return canalVenda;
	}

	public void setCanalVenda(String canalVenda) {
		this.canalVenda = canalVenda;
	}

	public String getStatusReserva() {
		return statusReserva;
	}

	public void setStatusReserva(String statusReserva) {
		this.statusReserva = statusReserva;
	}

	public BigDecimal getValorBruto() {
		return valorBruto;
	}

	public void setValorBruto(BigDecimal valorBruto) {
		this.valorBruto = valorBruto;
	}

	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public LocalDateTime getDataReserva() {
		return dataReserva;
	}

	public void setDataReserva(LocalDateTime dataReserva) {
		this.dataReserva = dataReserva;
	}

	public LocalDateTime getDataConfirmacao() {
		return dataConfirmacao;
	}

	public void setDataConfirmacao(LocalDateTime dataConfirmacao) {
		this.dataConfirmacao = dataConfirmacao;
	}

	public LocalDateTime getDataCancelamento() {
		return dataCancelamento;
	}

	public void setDataCancelamento(LocalDateTime dataCancelamento) {
		this.dataCancelamento = dataCancelamento;
	}
	
}
