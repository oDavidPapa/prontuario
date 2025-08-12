package com.ufes.prontuario.service;

import com.ufes.prontuario.dto.contato.ContatoConverter;
import com.ufes.prontuario.dto.medico.MedicoCadastroDTO;
import com.ufes.prontuario.dto.medico.MedicoConverter;
import com.ufes.prontuario.dto.medico.MedicoDTO;
import com.ufes.prontuario.exception.RecursoNaoEncontradoException;
import com.ufes.prontuario.model.Medico;
import com.ufes.prontuario.repository.MedicoRepository;
import com.ufes.prontuario.specification.BaseSpecification;
import com.ufes.prontuario.util.CodeUtils;
import com.ufes.prontuario.util.PageUtils;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
@Log4j2
public class MedicoService implements IBaseService<MedicoCadastroDTO, Medico> {

    private final MedicoRepository repository;
    private final PessoaService pessoaService;
    private final ContatoService contatoService;

    public Medico findById(Long id) {
        log.info("Buscando medico id={}", id);
        return this.repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Medico", id));
    }

    public Page<Medico> filter(
            Long id, String nome, String cpf, String especialidade, String crm, Pageable pageable) {
        var specification = this.prepareSpecification(id, nome, cpf, especialidade, crm);

        return this.repository.findAll(specification, PageUtils.preparePageable(pageable));
    }

    public List<Medico> findAll() {
        return this.repository.findAll();
    }

    private Specification<Medico> prepareSpecification(
            Long id, String nome, String cpf, String especialidade, String crm) {
        final var specification = new BaseSpecification<Medico>();

        return specification
                .and(specification.findById(id))
                .and(specification.findLikeByColumn("especialidade", especialidade))
                .and(specification.findLikeByColumn("crm", crm))
                .and(specification.findLikeBySubColumn("pessoa", "nome", nome))
                .and(specification.findLikeBySubColumn("pessoa", "cpf", CodeUtils.getDigtsOnly(cpf)));

    }

    public List<Medico> listar() {
        return this.repository.findAll();
    }

    @Transactional
    public Medico inserir(MedicoCadastroDTO medicoCadastroDTO) {
        log.info("Insert medico... ");
        return Optional.ofNullable(medicoCadastroDTO)
                .map(this::validarInsert)
                .map(this::prepareInsert)
                .map(repository::save)
                .orElseThrow();
    }

    @Transactional
    public Medico update(Long id, MedicoCadastroDTO medicoCadastroDTO) {
        log.info("Update medico id={}", id);
        return Optional.ofNullable(medicoCadastroDTO)
                .map(aDto -> validarUpdate(aDto, id))
                .map(medico -> prepareUpdate(medico, id))
                .map(this.repository::save)
                .orElseThrow();
    }

    public void delete(Long id) {
        log.info("Delete medico id={}", id);
        var medico = this.findById(id);

        Optional.ofNullable(medico)
                .ifPresent(p -> {
                    this.validarDelete(p);
                    this.repository.delete(p);
                });
    }

    @Override
    public MedicoCadastroDTO validarInsert(MedicoCadastroDTO dtoCadastro) {
        return dtoCadastro;
    }

    @Override
    public MedicoCadastroDTO validarUpdate(MedicoCadastroDTO dtoCadastro, Long id) {
        return dtoCadastro;
    }

    @Override
    public void validarDelete(Medico entity) {

    }

    @Override
    public Medico prepareInsert(MedicoCadastroDTO dtoCadastro) {
        var medico = MedicoConverter.toEntity(dtoCadastro);
        var pessoa = this.pessoaService.inserir(dtoCadastro.getPessoaCadastroDTO());

        medico.setPessoa(pessoa);

        return medico;
    }

    @Override
    public Medico prepareUpdate(MedicoCadastroDTO dtoCadastro, Long id) {
        var medico = this.findById(id);
        medico.setEspecialidade(dtoCadastro.getEspecialidade());
        medico.setCrm(dtoCadastro.getCrm());

        return medico;
    }

    public MedicoDTO setContatoPrincial(MedicoDTO medicoDTO) {
        var contato = contatoService.getContatoPrincipalByPessoa(medicoDTO.getPessoa().getId());
        if (Objects.nonNull(contato)) {
            medicoDTO.setContato(ContatoConverter.toDTO(contato));
        }
        return medicoDTO;
    }

    public Medico getMedicoByPessoaId(Long idPessoa) {
        return this.repository.findByPessoaId(idPessoa);
    }
}
