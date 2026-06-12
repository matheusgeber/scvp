package com.scvp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ponto_venda", schema = "SCVP")
public class PontoVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ponto_venda")
    private Long id;

    @Column(name = "nome", nullable = false, length = 120)
    private String nome;

    @Column(name = "tipo_ponto", length = 20)
    private String tipoPonto;

    @Column(name = "endereco", length = 255)
    private String endereco;

    @Column(name = "ativo")
    private Boolean ativo = true;

    public PontoVenda() {}

    public PontoVenda(Long id, String nome, String tipoPonto, String endereco, Boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.tipoPonto = tipoPonto;
        this.endereco = endereco;
        this.ativo = ativo;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipoPonto() {
		return tipoPonto;
	}

	public void setTipoPonto(String tipoPonto) {
		this.tipoPonto = tipoPonto;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}    
    
}
