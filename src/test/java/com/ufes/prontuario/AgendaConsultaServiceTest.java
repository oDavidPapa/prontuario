package com.ufes.prontuario;

import com.ufes.prontuario.exception.RecursoNaoEncontradoException;
import com.ufes.prontuario.model.AgendaConsulta;
import com.ufes.prontuario.repository.AgendaConsultaRepository;
import com.ufes.prontuario.service.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AgendaConsultaServiceTest {

    @Mock
    private AgendaConsultaRepository repository;
    @Mock
    private MedicoService medicoService;
    @Mock
    private PacienteService pacienteService;
    @Mock
    private AgendaService agendaService;
    @Mock
    private ConsultaService consultaService;

    @InjectMocks
    private AgendaConsultaService service;


    @Test
    void findById_deveRetornarAgendaConsulta_quandoEncontrado() {
        AgendaConsulta agenda = new AgendaConsulta();
        agenda.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(agenda));
        AgendaConsulta result = service.findById(1L);
        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void findById_deveLancarExcecao_quandoNaoEncontrado() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RecursoNaoEncontradoException.class,
                () -> service.findById(1L));
    }

    @Test
    void listar_deveRetornarListaDoRepository() {
        when(repository.findAll()).thenReturn(List.of(new AgendaConsulta()));
        List<AgendaConsulta> result = service.listar();
        assertEquals(1, result.size());
    }

    @Test
    void delete_deveRemoverAgendaConsulta_quandoEncontrada() {
        AgendaConsulta agenda = new AgendaConsulta();
        agenda.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(agenda));
        service.delete(1L);
        verify(repository).delete(agenda);
    }

    @Test
    void delete_deveLancarExcecao_quandoNaoEncontrada() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RecursoNaoEncontradoException.class,
                () -> service.delete(1L));
    }
}
