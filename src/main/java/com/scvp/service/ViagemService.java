package com.scvp.service;

import com.scvp.model.*;
import com.scvp.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

        if (aeroportoRepository.count() < 2) {
            throw new IllegalArgumentException(
                    "É necessário cadastrar pelo menos dois aeroportos para criar uma viagem."
            );
        }

        if (!"OPERACIONAL".equals(modal.getEstadoOperacional())) {
            throw new IllegalStateException(
                "Modal '" + modal.getIdentificacao() + "' está em manutenção e não pode ser utilizado."
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
        LocalDate dataSaida = LocalDate.parse(dataHoraSaida);
        
        if (dataSaida.isBefore(LocalDate.now())) {
        	throw new IllegalArgumentException("Data de saída não pode ser anterior à data atual.");
        }

        Viagem viagem = new Viagem();
        viagem.setModal(modal);
        viagem.setOrigemCidade(origemCidade);
        viagem.setDestinoCidade(destinoCidade);
        viagem.setOrigemAeroporto(origemAeroporto);
        viagem.setDestinoAeroporto(destinoAeroporto);        
        viagem.setDataHoraSaida(dataSaida.atStartOfDay());
        viagem.setDataHoraChegada(null);
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