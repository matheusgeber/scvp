package com.scvp.service;

import com.scvp.model.Cliente;
import com.scvp.repository.ClienteRepository;
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
class ClienteServiceTest {
 
    @Mock
    private ClienteRepository clienteRepository;
 
    @InjectMocks
    private ClienteService clienteService;
 
    private Cliente cliente;
 
    @BeforeEach
    void setup() {
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Marcos Costa");
        cliente.setCpf("12345678901");
        cliente.setDataNascimento(LocalDate.of(1990, 5, 10));
        cliente.setEmail("marcos@email.com");
        cliente.setTelefone("21999999999");
    }
 
    @Test
    void deveCadastrarClienteComSucesso() {
        when(clienteRepository.existsByCpf("12345678901")).thenReturn(false);
        when(clienteRepository.existsByEmail("marcos@email.com")).thenReturn(false);
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);
 
        Cliente resultado = clienteService.cadastrar(cliente);
 
        assertNotNull(resultado);
        assertEquals("Marcos Costa", resultado.getNome());
        verify(clienteRepository).save(cliente);
    }
 
    @Test
    void deveLancarExcecaoQuandoCpfJaCadastrado() {
        when(clienteRepository.existsByCpf("12345678901")).thenReturn(true);
 
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> clienteService.cadastrar(cliente));
 
        assertEquals("CPF já cadastrado.", exception.getMessage());
        verify(clienteRepository, never()).save(any());
    }
 
    @Test
    void deveLancarExcecaoQuandoEmailJaCadastrado() {
        when(clienteRepository.existsByCpf("12345678901")).thenReturn(false);
        when(clienteRepository.existsByEmail("marcos@email.com")).thenReturn(true);
 
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> clienteService.cadastrar(cliente));
 
        assertEquals("E-mail já cadastrado.", exception.getMessage());
        verify(clienteRepository, never()).save(any());
    }
 
    @Test
    void deveListarTodosOsClientes() {
        when(clienteRepository.findAll()).thenReturn(List.of(cliente, new Cliente()));
 
        List<Cliente> resultado = clienteService.listarTodos();
 
        assertEquals(2, resultado.size());
        verify(clienteRepository).findAll();
    }
 
    @Test
    void deveBuscarClientePorId() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
 
        Cliente resultado = clienteService.buscarPorId(1L);
 
        assertEquals(1L, resultado.getId());
        assertEquals("Marcos Costa", resultado.getNome());
    }
 
    @Test
    void deveLancarExcecaoQuandoClienteNaoExiste() {
        when(clienteRepository.findById(99L)).thenReturn(Optional.empty());
 
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> clienteService.buscarPorId(99L));
 
        assertEquals("Cliente não encontrado.", exception.getMessage());
    }
}