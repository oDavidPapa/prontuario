package com.ufes.prontuario;

import com.ufes.prontuario.dto.consulta.ConsultaCadastroDTO;
import com.ufes.prontuario.exception.RecursoNaoEncontradoException;
import com.ufes.prontuario.model.*;
import com.ufes.prontuario.repository.ConsultaRepository;
import com.ufes.prontuario.service.ConsultaService;
import com.ufes.prontuario.service.MedicoService;
import com.ufes.prontuario.service.PacienteService;
import com.ufes.prontuario.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
public class ConsultaServiceTest {

    @Mock
    private ConsultaRepository repository;

    @Mock
    private MedicoService medicoService;

    @Mock
    private PacienteService pacienteService;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private ConsultaService service;

    private Consulta consulta;
    private ConsultaCadastroDTO dto;
    private Paciente paciente;
    private Medico medico;
    private Usuario usuario;
    private Pessoa pessoa;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pessoa = new Pessoa();
        pessoa.setId(30L);
        pessoa.setCpf("1234567898");
        pessoa.setNome("Nome Pessoa");
        pessoa.setDataNascimento(LocalDate.now());

        paciente = new Paciente();
        paciente.setId(10L);

        medico = new Medico();
        medico.setId(20L);
        medico.setPessoa(pessoa);

        usuario = new Usuario();
        usuario.setId(30L);
        usuario.setPessoa(pessoa);
        consulta = new Consulta();
        consulta.setId(1L);
        dto = new ConsultaCadastroDTO();
        dto.setIdPaciente(10L);
        dto.setTipo("RETORNO");
        dto.setAnamnese("Paciente com sintomas leves");
        when(authentication.getName()).thenReturn("loginMedico");
    }

    @Test
    void findById_DeveRetornarConsulta_QuandoExistir() {
        when(repository.findById(1L)).thenReturn(Optional.of(consulta));
        Consulta resultado = service.findById(1L);
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
    }

    @Test
    void findById_DeveLancarExcecao_QuandoNaoExistir() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RecursoNaoEncontradoException.class, () -> service.findById(1L));
    }

    @Test
    void listar_DeveRetornarListaDeConsultas() {
        when(repository.findAll()).thenReturn(Arrays.asList(consulta));
        List<Consulta> lista = service.listar();
        assertEquals(1, lista.size());
    }

    @Test
    void inserir_DeveSalvarComSucesso() {
        when(usuarioService.findByUsuarioLogin("loginMedico")).thenReturn(usuario);
        when(medicoService.getMedicoByPessoaId(anyLong())).thenReturn(medico);
        when(pacienteService.findById(10L)).thenReturn(paciente);
        when(repository.save(any())).thenReturn(consulta);
        Consulta resultado = service.inserir(dto, authentication);
        assertNotNull(resultado);
        verify(repository, times(1)).save(any());
    }

    @Test
    void update_DeveAtualizarComSucesso() {
        when(repository.findById(1L)).thenReturn(Optional.of(consulta));
        when(pacienteService.findById(10L)).thenReturn(paciente);
        when(repository.save(any())).thenReturn(consulta);
        Consulta resultado = service.update(1L, dto);
        assertNotNull(resultado);
        verify(repository, times(1)).save(any());
    }

    @Test
    void save_DeveSalvarConsultaDiretamente() {
        when(repository.save(any())).thenReturn(consulta);
        Consulta resultado = service.save(consulta);
        assertNotNull(resultado);
    }

}
