package com.ufes.prontuario.service;

import com.ufes.prontuario.dto.diagnostico.DiagnosticoCadastroDTO;
import com.ufes.prontuario.dto.diagnostico.DiagnosticoConverter;
import com.ufes.prontuario.exception.RecursoNaoEncontradoException;
import com.ufes.prontuario.model.Diagnostico;
import com.ufes.prontuario.repository.DiagnosticoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class DiagnosticoService implements IBaseService<DiagnosticoCadastroDTO, Diagnostico> {

    private final DiagnosticoRepository repository;
    private final ConsultaService consultaService;

    public Diagnostico findById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Diagnostico", id));
    }

    public List<Diagnostico> listar() {
        return this.repository.findAll();
    }

    public Diagnostico inserir(DiagnosticoCadastroDTO diagnosticoCadastroDTO) {
        return Optional.ofNullable(diagnosticoCadastroDTO)
                .map(this::validarInsert)
                .map(this::prepareInsert)
                .map(repository::save)
                .orElseThrow();
    }

    public Diagnostico update(Long id, DiagnosticoCadastroDTO diagnosticoCadastroDTO) {
        return Optional.ofNullable(diagnosticoCadastroDTO)
                .map(aDto -> validarUpdate(aDto, id))
                .map(diagnostico -> prepareUpdate(diagnostico ,id))
                .map(this.repository::save)
                .orElseThrow();
    }

    public void delete(Long id) {
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
        diagnostico.setDescricao(dtoCadastro.getDescricao());

        return diagnostico;
    }
}
