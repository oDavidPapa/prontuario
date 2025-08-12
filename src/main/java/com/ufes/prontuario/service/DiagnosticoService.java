package com.ufes.prontuario.service;

import com.ufes.prontuario.dto.diagnostico.DiagnosticoCadastroDTO;
import com.ufes.prontuario.dto.diagnostico.DiagnosticoConverter;
import com.ufes.prontuario.exception.RecursoNaoEncontradoException;
import com.ufes.prontuario.model.Diagnostico;
import com.ufes.prontuario.repository.DiagnosticoRepository;
import com.ufes.prontuario.specification.BaseSpecification;
import com.ufes.prontuario.util.PageUtils;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Log4j2
public class DiagnosticoService implements IBaseService<DiagnosticoCadastroDTO, Diagnostico> {

    private final DiagnosticoRepository repository;
    private final ConsultaService consultaService;

    public Diagnostico findById(Long id) {
        log.info("Buscando Diagnostico id={}", id);
        return this.repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Diagnostico", id));
    }

    public Diagnostico findByIdConsulta(Long idConsulta) {
        log.info("Buscando diagnostico by consulta id={}", idConsulta);
        return this.repository.findByConsultaId(idConsulta);
    }

    public List<Diagnostico> listar() {
        return this.repository.findAll();
    }

    public Page<Diagnostico> filter(Long idConsulta, String descricao, Pageable pageable) {
        var specification = this.prepareSpecification(idConsulta, descricao);

        return this.repository.findAll(specification, PageUtils.preparePageable(pageable));
    }

    private Specification<Diagnostico> prepareSpecification(Long idConsulta, String descricao) {
        final var specification = new BaseSpecification<Diagnostico>();

        return specification
                .and(specification.findLikeByColumn("descricao", descricao))
                .and(specification.findBySubColumnId( "consulta", "id", idConsulta));

    }

    public Diagnostico inserir(DiagnosticoCadastroDTO diagnosticoCadastroDTO) {
        log.info("Insert diagnostico...");
        return Optional.ofNullable(diagnosticoCadastroDTO)
                .map(this::validarInsert)
                .map(this::prepareInsert)
                .map(repository::save)
                .orElseThrow();
    }

    public Diagnostico update(Long id, DiagnosticoCadastroDTO diagnosticoCadastroDTO) {
        log.info("Update diagnostico id={}", id);
        return Optional.ofNullable(diagnosticoCadastroDTO)
                .map(aDto -> validarUpdate(aDto, id))
                .map(diagnostico -> prepareUpdate(diagnostico ,id))
                .map(this.repository::save)
                .orElseThrow();
    }

    public void delete(Long id) {
        log.info("Delete diagnostico id={}", id);
        var diagnostico = this.findById(id);

        Optional.ofNullable(diagnostico)
                .ifPresent(d -> {
                    this.validarDelete(d);
                    this.repository.delete(d);
                });
    }

    @Override
    public DiagnosticoCadastroDTO validarInsert(DiagnosticoCadastroDTO dtoCadastro) {
        return dtoCadastro;
    }

    @Override
    public DiagnosticoCadastroDTO validarUpdate(DiagnosticoCadastroDTO dtoCadastro, Long id) {
        return dtoCadastro;
    }

    @Override
    public void validarDelete(Diagnostico entity) {

    }

    @Override
    public Diagnostico prepareInsert(DiagnosticoCadastroDTO dtoCadastro) {
        var diagnostico = DiagnosticoConverter.toEntity(dtoCadastro);
        diagnostico.setConsulta(consultaService.findById(dtoCadastro.getIdConsulta()));

        return diagnostico;
    }

    @Override
    public Diagnostico prepareUpdate(DiagnosticoCadastroDTO dtoCadastro, Long id) {
        var diagnostico = this.findById(id);

        diagnostico.setDiagnostico(dtoCadastro.getDiagnostico());

        return diagnostico;
    }
}
