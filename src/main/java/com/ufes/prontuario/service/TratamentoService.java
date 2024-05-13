package com.ufes.prontuario.service;

import com.ufes.prontuario.dto.tratamento.TratamentoCadastroDTO;
import com.ufes.prontuario.exception.RecursoNaoEncontradoException;
import com.ufes.prontuario.model.Tratamento;
import com.ufes.prontuario.repository.TratamentoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class TratamentoService implements IBaseService<TratamentoCadastroDTO, Tratamento>{

    private final TratamentoRepository repository;

    public Tratamento findById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Tratamento", id));
    }

    public List<Tratamento> listar() {
        return this.repository.findAll();
    }

    public Tratamento inserir(TratamentoCadastroDTO tratamentoCadastroDTO) {
        return Optional.ofNullable(tratamentoCadastroDTO)
                .map(this::validarInsert)
                .map(this::prepareInsert)
                .map(repository::save)
                .orElseThrow();
    }

    public Tratamento update(Long id, TratamentoCadastroDTO tratamentoCadastroDTO) {
        return Optional.ofNullable(tratamentoCadastroDTO)
                .map(aDto -> validarUpdate(aDto, id))
                .map(tratamento -> prepareUpdate(tratamento ,id))
                .map(this.repository::save)
                .orElseThrow();
    }

    public void delete(Long id) {
        var tratamento = this.findById(id);

        Optional.ofNullable(tratamento)
                .ifPresent(p -> {
                    this.validarDelete(p);
                    this.repository.delete(p);
                });
    }

    @Override
    public TratamentoCadastroDTO validarInsert(TratamentoCadastroDTO dtoCadastro) {
        return null;
    }

    @Override
    public TratamentoCadastroDTO validarUpdate(TratamentoCadastroDTO dtoCadastro, Long id) {
        return null;
    }

    @Override
    public void validarDelete(Tratamento entity) {

    }

    @Override
    public Tratamento prepareInsert(TratamentoCadastroDTO dtoCadastro) {
        return null;
    }

    @Override
    public Tratamento prepareUpdate(TratamentoCadastroDTO dtoCadastro, Long id) {
        return null;
    }
}
