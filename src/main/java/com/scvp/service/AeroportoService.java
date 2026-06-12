package com.scvp.service;

import com.scvp.model.Aeroporto;
import com.scvp.repository.AeroportoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AeroportoService {

    private final AeroportoRepository aeroportoRepository;

    public AeroportoService(AeroportoRepository aeroportoRepository) {
        this.aeroportoRepository = aeroportoRepository;
    }

    public Aeroporto cadastrar(Aeroporto aeroporto) {
        return aeroportoRepository.save(aeroporto);
    }

    public List<Aeroporto> listarTodos() {
        return aeroportoRepository.findAll();
    }

    public Aeroporto buscarPorId(Long id) {
        return aeroportoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Aeroporto não encontrado."));
    }
}