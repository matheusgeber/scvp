package com.scvp.service;

import com.scvp.model.Modal;
import com.scvp.repository.ModalRepository;

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
class ModalServiceTest {

    @Mock
    private ModalRepository modalRepository;

    @InjectMocks
    private ModalService modalService;

    private Modal modal;

    @BeforeEach
    void setup() {
        modal = new Modal();
        modal.setId(1L);
        modal.setIdentificacao("AZ123");
        modal.setEstadoOperacional("OPERACIONAL");
    }

    @Test
    void deveCadastrarModal() {

        when(modalRepository.save(any(Modal.class)))
                .thenReturn(modal);

        Modal resultado = modalService.cadastrar(modal);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("AZ123", resultado.getIdentificacao());

        verify(modalRepository).save(modal);
    }

    @Test
    void deveListarTodos() {

        when(modalRepository.findAll())
                .thenReturn(List.of(modal, new Modal()));

        List<Modal> modais = modalService.listarTodos();

        assertEquals(2, modais.size());

        verify(modalRepository).findAll();
    }

    @Test
    void deveBuscarModalPorId() {

        when(modalRepository.findById(1L))
                .thenReturn(Optional.of(modal));

        Modal resultado = modalService.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());

        verify(modalRepository).findById(1L);
    }

    @Test
    void deveLancarExcecaoQuandoModalNaoExiste() {

        when(modalRepository.findById(99L))
                .thenReturn(Optional.empty());

        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> modalService.buscarPorId(99L)
                );

        assertEquals("Modal não encontrado.", exception.getMessage());

        verify(modalRepository).findById(99L);
    }
}