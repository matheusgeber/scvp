package com.scvp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_funcionario")
    private Funcionario funcionario;

    @Column(name = "email_login", nullable = false, length = 150, unique = true)
    private String emailLogin;

    @Column(name = "senha_hash", nullable = false, length = 255)
    private String senhaHash;

    @Column(name = "perfil", length = 30)
    private String perfil;

    @Column(name = "status_sessao", length = 20)
    private String statusSessao;

    @Column(name = "ativo")
    private Boolean ativo = true;

    public Usuario() {}

    public Usuario(Long id, Cliente cliente, Funcionario funcionario, String emailLogin,
                    String senhaHash, String perfil, String statusSessao, Boolean ativo) {
        this.id = id;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.emailLogin = emailLogin;
        this.senhaHash = senhaHash;
        this.perfil = perfil;
        this.statusSessao = statusSessao;
        this.ativo = ativo;
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

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public String getEmailLogin() {
		return emailLogin;
	}

	public void setEmailLogin(String emailLogin) {
		this.emailLogin = emailLogin;
	}

	public String getSenhaHash() {
		return senhaHash;
	}

	public void setSenhaHash(String senhaHash) {
		this.senhaHash = senhaHash;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getStatusSessao() {
		return statusSessao;
	}

	public void setStatusSessao(String statusSessao) {
		this.statusSessao = statusSessao;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}    
    
}