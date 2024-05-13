package com.ufes.prontuario.service;

import com.ufes.prontuario.dto.medico.MedicoCadastroDTO;
import com.ufes.prontuario.dto.medico.MedicoCadastroDTO;
import com.ufes.prontuario.exception.RecursoNaoEncontradoException;
import com.ufes.prontuario.model.Medico;
import com.ufes.prontuario.model.Medico;
import com.ufes.prontuario.repository.MedicoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class MedicoService implements IBaseService<MedicoCadastroDTO, Medico> {

    public Medico findById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Medico", id));
    }

    public List<Medico> listar() {
        return this.repository.findAll();
    }

    public Medico inserir(MedicoCadastroDTO medicoCadastroDTO) {
        return Optional.ofNullable(medicoCadastroDTO)
                .map(this::validarInsert)
                .map(this::prepareInsert)
                .map(repository::save)
                .orElseThrow();
    }

    public Medico update(Long id, MedicoCadastroDTO medicoCadastroDTO) {
        return Optional.ofNullable(medicoCadastroDTO)
                .map(aDto -> validarUpdate(aDto, id))
                .map(medico -> prepareUpdate(medico ,id))
                .map(this.repository::save)
                .orElseThrow();
    }

    public void delete(Long id) {
        var medico = this.findById(id);

        Optional.ofNullable(medico)
                .ifPresent(p -> {
                    this.validarDelete(p);
                    this.repository.delete(p);
                });
    }

    private final MedicoRepository repository;

    @Override
    public MedicoCadastroDTO validarInsert(MedicoCadastroDTO dtoCadastro) {
        return null;
    }

    @Override
    public MedicoCadastroDTO validarUpdate(MedicoCadastroDTO dtoCadastro, Long id) {
        return null;
    }

    @Override
    public void validarDelete(Medico entity) {

    }

    @Override
    public Medico prepareInsert(MedicoCadastroDTO dtoCadastro) {
        return null;
    }

    @Override
    public Medico prepareUpdate(MedicoCadastroDTO dtoCadastro, Long id) {
        return null;
    }
}
