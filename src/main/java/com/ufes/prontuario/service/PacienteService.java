package com.ufes.prontuario.service;

import com.ufes.prontuario.dto.contato.ContatoConverter;
import com.ufes.prontuario.dto.endereco.EnderecoConverter;
import com.ufes.prontuario.dto.paciente.PacienteCadastroDTO;
import com.ufes.prontuario.dto.paciente.PacienteConverter;
import com.ufes.prontuario.dto.paciente.PacienteDTO;
import com.ufes.prontuario.exception.RecursoNaoEncontradoException;
import com.ufes.prontuario.model.Paciente;
import com.ufes.prontuario.repository.EnderecoRepository;
import com.ufes.prontuario.repository.PacienteRepository;
import com.ufes.prontuario.specification.BaseSpecification;
import com.ufes.prontuario.util.PageUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PacienteService implements IBaseService<PacienteCadastroDTO, Paciente> {

    private final PacienteRepository repository;
    private final PessoaService pessoaService;
    private final ContatoService contatoService;
    private final EnderecoSerivce enderecoSerivce;


    public Paciente findById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Paciente", id));
    }

    public List<Paciente> listar() {
        return this.repository.findAll();
    }

    public Page<Paciente> filter(Long id, String nome, String cpf, Pageable pageable) {
        var specification = this.prepareSpecification(id, nome, cpf);

        return this.repository.findAll(specification, PageUtils.preparePageable(pageable));
    }

    public List<Paciente> findAll() {
        return this.repository.findAll();
    }


    public PacienteDTO completeDTO(PacienteDTO pacienteDTO) {
        var contato = this.contatoService.getContatoPrincipalByPessoa(pacienteDTO.getPessoa().getId());
        var endereco = this.enderecoSerivce.findByPessoaId(pacienteDTO.getPessoa().getId());

        if(Objects.nonNull(contato)){
            pacienteDTO.setContato(ContatoConverter.toDTO(contato));
        }

        if(Objects.nonNull(endereco)){
            pacienteDTO.setEndereco(EnderecoConverter.toDTO(endereco));
        }

        return pacienteDTO;
    }       

    private Specification<Paciente> prepareSpecification(Long id, String nome, String cpf) {
        final var specification = new BaseSpecification<Paciente>();

        return specification
                .and(specification.findById(id))
                .and(specification.findLikeBySubColumn("pessoa", "nome", nome))
                .and(specification.findLikeBySubColumn("pessoa", "cpf", cpf));

    }

    @Transactional
    public Paciente inserir(PacienteCadastroDTO pacienteCadastroDTO) {
        return Optional.ofNullable(pacienteCadastroDTO)
                .map(this::validarInsert)
                .map(this::prepareInsert)
                .map(repository::save)
                .orElseThrow();
    }

    public Paciente update(Long id, PacienteCadastroDTO pacienteCadastroDTO) {
        return Optional.ofNullable(pacienteCadastroDTO)
                .map(pDto -> validarUpdate(pDto, id))
                .map(paciente -> prepareUpdate(paciente, id))
                .map(this.repository::save)
                .orElseThrow();
    }

    public void delete(Long id) {
        var paciente = this.findById(id);

        Optional.ofNullable(paciente)
                .ifPresent(p -> {
                    this.validarDelete(p);
                    this.repository.delete(p);
                });
    }


    @Override
    public PacienteCadastroDTO validarInsert(PacienteCadastroDTO dtoCadastro) {

        return dtoCadastro;
    }

    @Override
    public PacienteCadastroDTO validarUpdate(PacienteCadastroDTO dtoCadastro, Long id) {
        return dtoCadastro;
    }

    @Override
    public void validarDelete(Paciente entity) {
    }

    @Override
    public Paciente prepareInsert(PacienteCadastroDTO dtoCadastro) {
        var paciente = PacienteConverter.toEntity(dtoCadastro);
        var pessoa = this.pessoaService.inserir(dtoCadastro.getPessoaCadastroDTO());

        paciente.setPessoa(pessoa);

        return paciente;
    }

    @Override
    @Transactional
    public Paciente prepareUpdate(PacienteCadastroDTO dtoCadastro, Long id) {

        var paciente = this.findById(id);
        paciente.setPeso(dtoCadastro.getPeso());
        paciente.setAltura(dtoCadastro.getAltura());

        pessoaService.update(paciente.getPessoa().getId(),
                dtoCadastro.getPessoaCadastroDTO());

        return paciente;
    }
}
