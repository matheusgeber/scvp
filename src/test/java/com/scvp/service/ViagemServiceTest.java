package com.scvp.service;

import com.scvp.model.*;
import com.scvp.repository.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ViagemServiceTest {

    @Mock
    private ViagemRepository viagemRepository;

    @Mock
    private ModalRepository modalRepository;

    @Mock
    private CidadeRepository cidadeRepository;

    @Mock
    private AeroportoRepository aeroportoRepository;

    @InjectMocks
    private ViagemService viagemService;

    private Modal modal;
    private Cidade origem;
    private Cidade destino;
    private Aeroporto aeroportoOrigem;
    private Aeroporto aeroportoDestino;

    @BeforeEach
    void setup() {

        Transportadora transportadora =
                new Transportadora(1L, "Azul", "12345678901234", "Contato", true);

        modal = new Modal(
                1L,
                transportadora,
                "AEREO",
                "AZ123",
                150,
                "OPERACIONAL"
        );

        origem = new Cidade(1L, "Rio de Janeiro", "RJ", "RIO");
        destino = new Cidade(2L, "São Paulo", "SP", "SAO");

        aeroportoOrigem =
                new Aeroporto(1L, origem, "Galeão", "GIG");

        aeroportoDestino =
                new Aeroporto(2L, destino, "Congonhas", "CGH");
    }

    @Test
    void deveCadastrarViagemComSucesso() {

        when(modalRepository.findById(1L))
                .thenReturn(Optional.of(modal));

        when(aeroportoRepository.count())
                .thenReturn(2L);

        when(cidadeRepository.findById(1L))
                .thenReturn(Optional.of(origem));

        when(cidadeRepository.findById(2L))
                .thenReturn(Optional.of(destino));

        when(aeroportoRepository.findById(1L))
                .thenReturn(Optional.of(aeroportoOrigem));

        when(aeroportoRepository.findById(2L))
                .thenReturn(Optional.of(aeroportoDestino));

        when(viagemRepository.save(any(Viagem.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Viagem viagem = viagemService.cadastrarComIds(
                1L,
                1L,
                2L,
                1L,
                2L,
                LocalDate.now().plusDays(1).toString(),
                null
        );

        assertNotNull(viagem);
        assertEquals("PROGRAMADA", viagem.getStatusViagem());
        assertEquals(modal, viagem.getModal());
    }

    @Test
    void deveLancarExcecaoQuandoModalNaoExiste() {

        when(modalRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(
                IllegalArgumentException.class,
                () -> viagemService.cadastrarComIds(
                        99L,
                        1L,
                        2L,
                        1L,
                        2L,
                        LocalDate.now().plusDays(1).toString(),
                        null
                )
        );
    }

    @Test
    void deveLancarExcecaoQuandoHaMenosDeDoisAeroportos() {

        when(modalRepository.findById(1L))
                .thenReturn(Optional.of(modal));

        when(aeroportoRepository.count())
                .thenReturn(1L);

        assertThrows(
                IllegalArgumentException.class,
                () -> viagemService.cadastrarComIds(
                        1L,
                        1L,
                        2L,
                        1L,
                        2L,
                        LocalDate.now().plusDays(1).toString(),
                        null
                )
        );
    }

    @Test
    void deveLancarExcecaoQuandoModalNaoEstaOperacional() {

        modal.setEstadoOperacional("MANUTENCAO");

        when(modalRepository.findById(1L))
                .thenReturn(Optional.of(modal));

        when(aeroportoRepository.count())
                .thenReturn(2L);

        assertThrows(
                IllegalStateException.class,
                () -> viagemService.cadastrarComIds(
                        1L,
                        1L,
                        2L,
                        1L,
                        2L,
                        LocalDate.now().plusDays(1).toString(),
                        null
                )
        );
    }

    @Test
    void deveLancarExcecaoQuandoOrigemEDestinoSaoIguais() {

        when(modalRepository.findById(1L))
                .thenReturn(Optional.of(modal));

        when(aeroportoRepository.count())
                .thenReturn(2L);

        when(cidadeRepository.findById(1L))
                .thenReturn(Optional.of(origem));

        assertThrows(
                IllegalArgumentException.class,
                () -> viagemService.cadastrarComIds(
                        1L,
                        1L,
                        1L,
                        1L,
                        2L,
                        LocalDate.now().plusDays(1).toString(),
                        null
                )
        );
    }

    @Test
    void deveLancarExcecaoQuandoDataForAnteriorAoDiaAtual() {

        when(modalRepository.findById(1L))
                .thenReturn(Optional.of(modal));

        when(aeroportoRepository.count())
                .thenReturn(2L);

        when(cidadeRepository.findById(1L))
                .thenReturn(Optional.of(origem));

        when(cidadeRepository.findById(2L))
                .thenReturn(Optional.of(destino));

        assertThrows(
                IllegalArgumentException.class,
                () -> viagemService.cadastrarComIds(
                        1L,
                        1L,
                        2L,
                        null,
                        null,
                        LocalDate.now().minusDays(1).toString(),
                        null
                )
        );
    }

    @Test
    void deveBuscarPorId() {

        Viagem viagem = new Viagem();
        viagem.setId(1L);

        when(viagemRepository.findById(1L))
                .thenReturn(Optional.of(viagem));

        Viagem resultado = viagemService.buscarPorId(1L);

        assertEquals(1L, resultado.getId());
    }

    @Test
    void deveLancarExcecaoAoBuscarViagemInexistente() {

        when(viagemRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                IllegalArgumentException.class,
                () -> viagemService.buscarPorId(1L)
        );
    }

    @Test
    void deveListarTodos() {

        when(viagemRepository.findAll())
                .thenReturn(List.of(new Viagem(), new Viagem()));

        List<Viagem> viagens = viagemService.listarTodos();

        assertEquals(2, viagens.size());
    }

    @Test
    void deveCadastrarViagemSimples() {

        Viagem viagem = new Viagem();

        when(viagemRepository.save(any(Viagem.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Viagem resultado = viagemService.cadastrar(viagem);

        assertEquals("PROGRAMADA", resultado.getStatusViagem());
    }
}