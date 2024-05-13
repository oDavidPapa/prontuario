package com.ufes.prontuario.service;

import com.ufes.prontuario.dto.doencadiagnostico.DoencaDiagnosticoCadastroDTO;
import com.ufes.prontuario.exception.RecursoNaoEncontradoException;
import com.ufes.prontuario.model.DoencaDiagnostico;
import com.ufes.prontuario.repository.DoencaDiagnosticoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class DoencaDiagnosticoService implements IBaseService<DoencaDiagnosticoCadastroDTO, DoencaDiagnostico>{

    private final DoencaDiagnosticoRepository repository;

    public DoencaDiagnostico findById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("DoencaDiagnostico", id));
    }

    public List<DoencaDiagnostico> listar() {
        return this.repository.findAll();
    }

    public DoencaDiagnostico inserir(DoencaDiagnosticoCadastroDTO doencaDiagnosticoCadastroDTO) {
        return Optional.ofNullable(doencaDiagnosticoCadastroDTO)
                .map(this::validarInsert)
                .map(this::prepareInsert)
                .map(repository::save)
                .orElseThrow();
    }

    public DoencaDiagnostico update(Long id, DoencaDiagnosticoCadastroDTO doencaDiagnosticoCadastroDTO) {
        return Optional.ofNullable(doencaDiagnosticoCadastroDTO)
                .map(aDto -> validarUpdate(aDto, id))
                .map(doencaDiagnostico -> prepareUpdate(doencaDiagnostico ,id))
                .map(this.repository::save)
                .orElseThrow();
    }

    public void delete(Long id) {
        var doencaDiagnostico = this.findById(id);

        Optional.ofNullable(doencaDiagnostico)
                .ifPresent(dd -> {
                    this.validarDelete(dd);
                    this.repository.delete(dd);
                });
    }

    @Override
    public DoencaDiagnosticoCadastroDTO validarInsert(DoencaDiagnosticoCadastroDTO dtoCadastro) {
        return null;
    }

    @Override
    public DoencaDiagnosticoCadastroDTO validarUpdate(DoencaDiagnosticoCadastroDTO dtoCadastro, Long id) {
        return null;
    }

    @Override
    public void validarDelete(DoencaDiagnostico entity) {

    }

    @Override
    public DoencaDiagnostico prepareInsert(DoencaDiagnosticoCadastroDTO dtoCadastro) {
        return null;
    }

    @Override
    public DoencaDiagnostico prepareUpdate(DoencaDiagnosticoCadastroDTO dtoCadastro, Long id) {
        return null;
    }
}
