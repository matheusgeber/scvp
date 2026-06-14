package com.scvp.service;

import com.scvp.model.*;
import com.scvp.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ViagemService {

    private final ViagemRepository viagemRepository;
    private final ModalRepository modalRepository;
    private final CidadeRepository cidadeRepository;
    private final AeroportoRepository aeroportoRepository;

    public ViagemService(ViagemRepository viagemRepository, ModalRepository modalRepository,
                          CidadeRepository cidadeRepository, AeroportoRepository aeroportoRepository) {
        this.viagemRepository = viagemRepository;
        this.modalRepository = modalRepository;
        this.cidadeRepository = cidadeRepository;
        this.aeroportoRepository = aeroportoRepository;
    }

    public Viagem cadastrarComIds(Long idModal, Long idOrigemCidade, Long idDestinoCidade,
                                   Long idOrigemAeroporto, Long idDestinoAeroporto, String dataHoraSaida,
                                   String dataHoraChegada) {

        Modal modal = modalRepository.findById(idModal).orElseThrow(() -> new IllegalArgumentException("Modal não encontrado."));

        if (!"OPERACIONAL".equals(modal.getEstadoOperacional())) {
            throw new IllegalStateException(
                "Modal '" + modal.getIdentificacao() + "' está em manutenção e não pode ser utilizado (RN001)."
            );
        }

        Cidade origemCidade = cidadeRepository.findById(idOrigemCidade).orElseThrow(() -> new IllegalArgumentException("Cidade de origem não encontrada."));

        Cidade destinoCidade = cidadeRepository.findById(idDestinoCidade).orElseThrow(() -> new IllegalArgumentException("Cidade de destino não encontrada."));

        if (idOrigemCidade.equals(idDestinoCidade)) {
            throw new IllegalArgumentException("Cidade de origem e destino não podem ser iguais.");
        }

        Aeroporto origemAeroporto = null;
        if (idOrigemAeroporto != null && idOrigemAeroporto > 0) {
            origemAeroporto = aeroportoRepository.findById(idOrigemAeroporto).orElseThrow(() -> new IllegalArgumentException("Aeroporto de origem não encontrado."));
        }

        Aeroporto destinoAeroporto = null;
        if (idDestinoAeroporto != null && idDestinoAeroporto > 0) {
            destinoAeroporto = aeroportoRepository.findById(idDestinoAeroporto).orElseThrow(() -> new IllegalArgumentException("Aeroporto de destino não encontrado."));
        }

        LocalDateTime saida = LocalDateTime.parse(dataHoraSaida);
        LocalDateTime chegada = dataHoraChegada != null && !dataHoraChegada.isBlank()
                ? LocalDateTime.parse(dataHoraChegada)
                : null;

        if (chegada != null && !chegada.isAfter(saida)) {
            throw new IllegalArgumentException("Data/hora de chegada deve ser posterior à saída.");
        }

        Viagem viagem = new Viagem();
        viagem.setModal(modal);
        viagem.setOrigemCidade(origemCidade);
        viagem.setDestinoCidade(destinoCidade);
        viagem.setOrigemAeroporto(origemAeroporto);
        viagem.setDestinoAeroporto(destinoAeroporto);
        viagem.setDataHoraSaida(saida);
        viagem.setDataHoraChegada(chegada);
        viagem.setStatusViagem("PROGRAMADA");

        return viagemRepository.save(viagem);
    }

    public Viagem cadastrar(Viagem viagem) {
        viagem.setStatusViagem("PROGRAMADA");
        return viagemRepository.save(viagem);
    }

    public List<Viagem> listarTodos() {
        return viagemRepository.findAll();
    }

    public Viagem buscarPorId(Long id) {
        return viagemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Viagem não encontrada."));
    }
}