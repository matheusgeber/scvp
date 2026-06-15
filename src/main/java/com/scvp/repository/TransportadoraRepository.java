package com.scvp.repository;

import com.scvp.model.Transportadora;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransportadoraRepository extends JpaRepository<Transportadora, Long> {
	boolean existsByCnpj(String cnpj);
}