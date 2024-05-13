package com.ufes.prontuario.service;

import com.ufes.prontuario.dto.doenca.DoencaCadastroDTO;
import com.ufes.prontuario.exception.RecursoNaoEncontradoException;
import com.ufes.prontuario.model.Doenca;
import com.ufes.prontuario.repository.DoencaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class DoencaService implements IBaseService<DoencaCadastroDTO, Doenca>{

    private final DoencaRepository repository;

    public Doenca findById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Doenca", id));
    }

    public List<Doenca> listar() {
        return this.repository.findAll();
    }

    public Doenca inserir(DoencaCadastroDTO doencaCadastroDTO) {
        return Optional.ofNullable(doencaCadastroDTO)
                .map(this::validarInsert)
                .map(this::prepareInsert)
                .map(repository::save)
                .orElseThrow();
    }

    public Doenca update(Long id, DoencaCadastroDTO doencaCadastroDTO) {
        return Optional.ofNullable(doencaCadastroDTO)
                .map(dDto -> validarUpdate(dDto, id))
                .map(doenca -> prepareUpdate(doenca ,id))
                .map(this.repository::save)
                .orElseThrow();
    }

    public void delete(Long id) {
        var doenca = this.findById(id);

        Optional.ofNullable(doenca)
                .ifPresent(p -> {
                    this.validarDelete(p);
                    this.repository.delete(p);
                });
    }

    @Override
    public DoencaCadastroDTO validarInsert(DoencaCadastroDTO dtoCadastro) {
        return null;
    }

    @Override
    public DoencaCadastroDTO validarUpdate(DoencaCadastroDTO dtoCadastro, Long id) {
        return null;
    }

    @Override
    public void validarDelete(Doenca entity) {

    }

    @Override
    public Doenca prepareInsert(DoencaCadastroDTO dtoCadastro) {
        return null;
    }

    @Override
    public Doenca prepareUpdate(DoencaCadastroDTO dtoCadastro, Long id) {
        return null;
    }
}

