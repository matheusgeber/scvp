package com.scvp.service;

import com.scvp.model.Transportadora;
import com.scvp.repository.TransportadoraRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransportadoraService {

    private final TransportadoraRepository transportadoraRepository;

    public TransportadoraService(TransportadoraRepository transportadoraRepository) {
        this.transportadoraRepository = transportadoraRepository;
    }

    public Transportadora cadastrar(Transportadora transportadora) {
    	if (transportadoraRepository.existsByCnpj(transportadora.getCnpj())) {
            throw new IllegalArgumentException("CNPJ já cadastrado.");
        }
        return transportadoraRepository.save(transportadora);
    }

    public List<Transportadora> listarTodos() {
        return transportadoraRepository.findAll();
    }

    public Transportadora buscarPorId(Long id) {
        return transportadoraRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Transportadora não encontrada."));
    }
}