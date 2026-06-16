package com.scvp.service;

import com.scvp.model.Transportadora;
import com.scvp.repository.TransportadoraRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransportadoraServiceTest {

    @Mock
    private TransportadoraRepository transportadoraRepository;

    @InjectMocks
    private TransportadoraService transportadoraService;

    private Transportadora transportadora;

    @BeforeEach
    void setup() {
        transportadora = new Transportadora(
                1L,
                "Azul",
                "12345678901234",
                "Contato",
                true
        );
    }

    @Test
    void deveCadastrarTransportadora() {

        when(transportadoraRepository.existsByCnpj("12345678901234"))
                .thenReturn(false);

        when(transportadoraRepository.save(any(Transportadora.class)))
                .thenReturn(transportadora);

        Transportadora resultado =
                transportadoraService.cadastrar(transportadora);

        assertNotNull(resultado);
        assertEquals("Azul", resultado.getNome());

        verify(transportadoraRepository).save(transportadora);
    }

    @Test
    void deveLancarExcecaoQuandoCnpjJaExiste() {

        when(transportadoraRepository.existsByCnpj("12345678901234"))
                .thenReturn(true);

        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> transportadoraService.cadastrar(transportadora)
                );

        assertEquals(
                "CNPJ já cadastrado.",
                exception.getMessage()
        );
    }

    @Test
    void deveListarTodasTransportadoras() {

        when(transportadoraRepository.findAll())
                .thenReturn(List.of(
                        transportadora,
                        new Transportadora()
                ));

        List<Transportadora> resultado =
                transportadoraService.listarTodos();

        assertEquals(2, resultado.size());
    }

    @Test
    void deveBuscarTransportadoraPorId() {

        when(transportadoraRepository.findById(1L))
                .thenReturn(Optional.of(transportadora));

        Transportadora resultado =
                transportadoraService.buscarPorId(1L);

        assertEquals(1L, resultado.getId());
    }

    @Test
    void deveLancarExcecaoQuandoTransportadoraNaoExiste() {

        when(transportadoraRepository.findById(99L))
                .thenReturn(Optional.empty());

        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> transportadoraService.buscarPorId(99L)
                );

        assertEquals(
                "Transportadora não encontrada.",
                exception.getMessage()
        );
    }
}