package com.scvp.service;

import com.scvp.model.Cidade;
import com.scvp.repository.CidadeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeService {

    private final CidadeRepository cidadeRepository;

    public CidadeService(CidadeRepository cidadeRepository) {
        this.cidadeRepository = cidadeRepository;
    }

    public Cidade cadastrar(Cidade cidade) {
        return cidadeRepository.save(cidade);
    }

    public List<Cidade> listarTodos() {
        return cidadeRepository.findAll();
    }

    public Cidade buscarPorId(Long id) {
        return cidadeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cidade não encontrada."));
    }
}