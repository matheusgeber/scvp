package com.scvp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "funcionario", schema = "SCVP")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_funcionario")
    private Long id;

    @Column(name = "nome", nullable = false, length = 150)
    private String nome;

    @Column(name = "cpf", nullable = false, length = 11, unique = true)
    private String cpf;

    @Column(name = "cargo", length = 80)
    private String cargo;

    @Column(name = "eh_gerente_virtual")
    private Boolean ehGerenteVirtual = false;

    @Column(name = "ativo")
    private Boolean ativo = true;

    public Funcionario() {}

    public Funcionario(Long id, String nome, String cpf, String cargo,
                        Boolean ehGerenteVirtual, Boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.cargo = cargo;
        this.ehGerenteVirtual = ehGerenteVirtual;
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public Boolean getEhGerenteVirtual() {
		return ehGerenteVirtual;
	}

	public void setEhGerenteVirtual(Boolean ehGerenteVirtual) {
		this.ehGerenteVirtual = ehGerenteVirtual;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
   
}