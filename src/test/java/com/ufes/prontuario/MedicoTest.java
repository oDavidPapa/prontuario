package com.ufes.prontuario;

import com.ufes.prontuario.dto.medico.MedicoCadastroDTO;
import com.ufes.prontuario.dto.medico.MedicoDTO;
import com.ufes.prontuario.dto.pessoa.PessoaDTO;
import com.ufes.prontuario.enums.TipoContatoEnum;
import com.ufes.prontuario.exception.RecursoNaoEncontradoException;
import com.ufes.prontuario.model.Contato;
import com.ufes.prontuario.model.Medico;
import com.ufes.prontuario.model.Pessoa;
import com.ufes.prontuario.repository.MedicoRepository;
import com.ufes.prontuario.service.ContatoService;
import com.ufes.prontuario.service.MedicoService;
import com.ufes.prontuario.service.PessoaService;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
public class MedicoTest {

    @Mock
    private MedicoRepository repository;

    @Mock
    private PessoaService pessoaService;

    @Mock
    private ContatoService contatoService;

    @InjectMocks
    private MedicoService service;

    private Medico medico;
    private MedicoCadastroDTO cadastroDTO;
    private Pessoa pessoa;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        pessoa = new Pessoa();
        pessoa.setId(100L);

        medico = new Medico();
        medico.setId(1L);
        medico.setPessoa(pessoa);

        cadastroDTO = new MedicoCadastroDTO();
        cadastroDTO.setCrm("12345");
        cadastroDTO.setEspecialidade("Cardiologia");
        cadastroDTO.setPessoaCadastroDTO(null);
    }

    @Test
    void findById_DeveRetornarMedico_QuandoExistir() {
        when(repository.findById(1L)).thenReturn(Optional.of(medico));
        Medico resultado = service.findById(1L);
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
    }

    @Test
    void findById_DeveLancarExcecao_QuandoNaoExistir() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(RecursoNaoEncontradoException.class, () -> service.findById(1L));
    }

    @Test
    void findAll_DeveRetornarLista() {
        when(repository.findAll()).thenReturn(Arrays.asList(medico));
        List<Medico> lista = service.findAll();

        assertEquals(1, lista.size());
    }

    @Test
    void listar_DeveRetornarLista() {
        when(repository.findAll()).thenReturn(Arrays.asList(medico));
        List<Medico> lista = service.listar();

        assertEquals(1, lista.size());
    }

    @Test
    void inserir_DeveSalvarMedico() {
        when(pessoaService.inserir(any())).thenReturn(pessoa);
        when(repository.save(any())).thenReturn(medico);
        Medico resultado = service.inserir(cadastroDTO);

        assertNotNull(resultado);
        verify(repository, times(1)).save(any());
    }

    @Test
    void update_DeveAtualizarMedico() {
        when(repository.findById(1L)).thenReturn(Optional.of(medico));
        when(repository.save(any())).thenReturn(medico);
        Medico resultado = service.update(1L, cadastroDTO);

        assertNotNull(resultado);
        verify(repository, times(1)).save(any());
    }

    @Test
    void delete_DeveRemoverMedico() {
        when(repository.findById(1L)).thenReturn(Optional.of(medico));
        service.delete(1L);

        verify(repository, times(1)).delete((Medico) any());
    }

    @Test
    void setContatoPrincial_DeveAdicionarContatoAoMedicoDTO() {
        MedicoDTO medicoDTO = new MedicoDTO();
        var pessoaDTO = new PessoaDTO();
        pessoaDTO.setId(100L);
        medicoDTO.setPessoa(pessoaDTO);
        Contato contato = new Contato();
        contato.setPessoa(pessoa);
        contato.setTipoContato(TipoContatoEnum.PRINCIPAL);
        when(contatoService.getContatoPrincipalByPessoa(100L)).thenReturn(contato);
        MedicoDTO resultado = service.setContatoPrincial(medicoDTO);

        assertNotNull(resultado.getContato());
    }

    @Test
    void getMedicoByPessoaId_DeveRetornarMedico() {
        when(repository.findByPessoaId(100L)).thenReturn(medico);
        Medico resultado = service.getMedicoByPessoaId(100L);

        assertEquals(medico, resultado);
    }
}

