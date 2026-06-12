package com.scvp.service;

import com.scvp.model.Viagem;
import com.scvp.repository.ViagemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViagemService {

    private final ViagemRepository viagemRepository;

    public ViagemService(ViagemRepository viagemRepository) {
        this.viagemRepository = viagemRepository;
    }

    public Viagem cadastrar(Viagem viagem) {
        viagem.setStatusViagem("Programada");
        return viagemRepository.save(viagem);
    }

    public List<Viagem> listarTodos() {
        return viagemRepository.findAll();
    }

    public Viagem buscarPorId(Long id) {
        return viagemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Viagem não encontrada."));
    }
}