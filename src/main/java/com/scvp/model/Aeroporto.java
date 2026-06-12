package com.scvp.model;

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
@Table(name = "aeroporto", schema = "SCVP")
public class Aeroporto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aeroporto")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cidade", nullable = false)
    private Cidade cidade;

    @Column(name = "nome", nullable = false, length = 150)
    private String nome;

    @Column(name = "codigo_iata", length = 3)
    private String codigoIata;

    public Aeroporto() {}

    public Aeroporto(Long id, Cidade cidade, String nome, String codigoIata) {
        this.id = id;
        this.cidade = cidade;
        this.nome = nome;
        this.codigoIata = codigoIata;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigoIata() {
		return codigoIata;
	}

	public void setCodigoIata(String codigoIata) {
		this.codigoIata = codigoIata;
	}
        
}
