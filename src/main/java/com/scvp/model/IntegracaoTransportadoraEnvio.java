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
@Table(name = "integracao_transportadora_envio")
public class IntegracaoTransportadoraEnvio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_envio")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reserva", nullable = false)
    private Reserva reserva;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_transportadora", nullable = false)
    private Transportadora transportadora;

    @Column(name = "data_envio")
    private LocalDateTime dataEnvio;

    @Column(name = "status_envio", length = 20)
    private String statusEnvio;

    @Column(name = "protocolo_envio", length = 120)
    private String protocoloEnvio;

    public IntegracaoTransportadoraEnvio() {}

    public IntegracaoTransportadoraEnvio(Long id, Reserva reserva, Transportadora transportadora,
                                          LocalDateTime dataEnvio, String statusEnvio, String protocoloEnvio) {
        this.id = id;
        this.reserva = reserva;
        this.transportadora = transportadora;
        this.dataEnvio = dataEnvio;
        this.statusEnvio = statusEnvio;
        this.protocoloEnvio = protocoloEnvio;
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

	public Transportadora getTransportadora() {
		return transportadora;
	}

	public void setTransportadora(Transportadora transportadora) {
		this.transportadora = transportadora;
	}

	public LocalDateTime getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(LocalDateTime dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public String getStatusEnvio() {
		return statusEnvio;
	}

	public void setStatusEnvio(String statusEnvio) {
		this.statusEnvio = statusEnvio;
	}

	public String getProtocoloEnvio() {
		return protocoloEnvio;
	}

	public void setProtocoloEnvio(String protocoloEnvio) {
		this.protocoloEnvio = protocoloEnvio;
	}
	
}
