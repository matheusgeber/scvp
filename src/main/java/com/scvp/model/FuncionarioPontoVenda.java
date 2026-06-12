package com.scvp.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "funcionario_ponto_venda", schema = "SCVP")
@IdClass(FuncionarioPontoVendaId.class)
public class FuncionarioPontoVenda {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_funcionario")
    private Funcionario funcionario;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ponto_venda")
    private PontoVenda pontoVenda;

    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @Column(name = "data_fim")
    private LocalDate dataFim;

    @Column(name = "autorizado_por_gerente")
    private Boolean autorizadoPorGerente;

    public FuncionarioPontoVenda() {}

    public FuncionarioPontoVenda(Funcionario funcionario, PontoVenda pontoVenda, LocalDate dataInicio,
                                  LocalDate dataFim, Boolean autorizadoPorGerente) {
        this.funcionario = funcionario;
        this.pontoVenda = pontoVenda;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.autorizadoPorGerente = autorizadoPorGerente;
    }

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public PontoVenda getPontoVenda() {
		return pontoVenda;
	}

	public void setPontoVenda(PontoVenda pontoVenda) {
		this.pontoVenda = pontoVenda;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

	public Boolean getAutorizadoPorGerente() {
		return autorizadoPorGerente;
	}

	public void setAutorizadoPorGerente(Boolean autorizadoPorGerente) {
		this.autorizadoPorGerente = autorizadoPorGerente;
	}
    
}
