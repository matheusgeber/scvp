package com.scvp.model;

import java.io.Serializable;
import java.util.Objects;

public class FuncionarioPontoVendaId implements Serializable {

    private static final long serialVersionUID = 1L;
    
	private Long funcionario;
    private Long pontoVenda;

    public FuncionarioPontoVendaId() {}

    public FuncionarioPontoVendaId(Long funcionario, Long pontoVenda) {
        this.funcionario = funcionario;
        this.pontoVenda = pontoVenda;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FuncionarioPontoVendaId)) return false;
        FuncionarioPontoVendaId that = (FuncionarioPontoVendaId) o;
        return Objects.equals(funcionario, that.funcionario)
                && Objects.equals(pontoVenda, that.pontoVenda);
    }

    @Override
    public int hashCode() {
        return Objects.hash(funcionario, pontoVenda);
    }
}