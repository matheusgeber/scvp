package com.scvp.service;

import com.scvp.model.Modal;
import com.scvp.repository.ModalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModalService {

    private final ModalRepository modalRepository;

    public ModalService(ModalRepository modalRepository) {
        this.modalRepository = modalRepository;
    }

    public Modal cadastrar(Modal modal) {
        return modalRepository.save(modal);
    }

    public List<Modal> listarTodos() {
        return modalRepository.findAll();
    }

    public Modal buscarPorId(Long id) {
        return modalRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Modal não encontrado."));
    }
}