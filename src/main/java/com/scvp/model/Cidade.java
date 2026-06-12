package com.scvp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cidade")
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cidade")
    private Long id;

    @Column(name = "nome", nullable = false, length = 120)
    private String nome;

    @Column(name = "uf", nullable = false, length = 2)
    private String uf;

    @Column(name = "codigo_3letras", length = 3)
    private String codigo3letras;

    public Cidade() {}

    public Cidade(Long id, String nome, String uf, String codigo3letras) {
        this.id = id;
        this.nome = nome;
        this.uf = uf;
        this.codigo3letras = codigo3letras;
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

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCodigo3letras() {
		return codigo3letras;
	}

	public void setCodigo3letras(String codigo3letras) {
		this.codigo3letras = codigo3letras;
	}    
    
}