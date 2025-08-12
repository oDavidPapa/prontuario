package com.ufes.prontuario;
import com.ufes.prontuario.dto.alergiapaciente.AlergiaPacienteCadastroDTO;
import com.ufes.prontuario.exception.RecursoNaoEncontradoException;
import com.ufes.prontuario.model.AlergiaPaciente;
import com.ufes.prontuario.repository.AlergiaPacienteRepository;
import com.ufes.prontuario.service.AlergiaPacienteService;
import com.ufes.prontuario.service.PacienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AlergiaPacienteServiceTest {

    @Mock
    private AlergiaPacienteRepository repository;

    @Mock
    private PacienteService pacienteService;

    @InjectMocks
    private AlergiaPacienteService service;

    private AlergiaPaciente alergia;
    private AlergiaPacienteCadastroDTO dto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        alergia = new AlergiaPaciente();
        alergia.setId(1L);
        alergia.setDescricao("Alergia a poeira");
        dto = new AlergiaPacienteCadastroDTO();
        dto.setDescricao("Alergia a poeira");
        dto.setIdPaciente(10L);
    }

    @Test
    void findById_DeveRetornarAlergiaPaciente_QuandoExistir() {
        when(repository.findById(1L)).thenReturn(Optional.of(alergia));
        AlergiaPaciente resultado = service.findById(1L);
        assertNotNull(resultado);
        assertEquals("Alergia a poeira", resultado.getDescricao());
    }

    @Test
    void findById_DeveLancarExcecao_QuandoNaoExistir() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RecursoNaoEncontradoException.class, () -> service.findById(1L));
    }

    @Test
    void listar_DeveRetornarLista() {
        when(repository.findAll()).thenReturn(Arrays.asList(alergia));
        List<AlergiaPaciente> lista = service.listar();
        assertEquals(1, lista.size());
        assertEquals("Alergia a poeira", lista.get(0).getDescricao());
    }

    @Test
    void findAllByPaciente_DeveRetornarLista() {
        when(repository.findAllByPacienteId(10L)).thenReturn(Arrays.asList(alergia));
        List<AlergiaPaciente> lista = service.findAllByPaciente(10L);
        assertEquals(1, lista.size());
        assertEquals("Alergia a poeira", lista.get(0).getDescricao());
    }

    @Test
    void inserir_DeveSalvarEAtribuirPaciente() {
        when(pacienteService.findById(10L)).thenReturn(null);
        when(repository.save(any())).thenReturn(alergia);
        AlergiaPaciente resultado = service.inserir(dto);
        assertNotNull(resultado);
        verify(repository, times(1)).save(any());
    }

    @Test
    void update_DeveSalvarAlergiaAtualizada() {
        when(repository.findById(1L)).thenReturn(Optional.of(alergia));
        when(pacienteService.findById(10L)).thenReturn(null);
        when(repository.save(any())).thenReturn(alergia);
        AlergiaPaciente resultado = service.update(1L, dto);
        assertNotNull(resultado);
        verify(repository, times(1)).save(any());
    }

    @Test
    void delete_DeveExcluirQuandoEncontrado() {
        when(repository.findById(1L)).thenReturn(Optional.of(alergia));
        service.delete(1L);
        verify(repository, times(1)).delete(alergia);
    }
}