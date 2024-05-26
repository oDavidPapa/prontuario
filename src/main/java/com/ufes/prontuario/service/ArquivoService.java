package com.ufes.prontuario.service;

import com.ufes.prontuario.dto.arquivo.ArquivoCadastroDTO;
import com.ufes.prontuario.exception.RecursoNaoEncontradoException;
import com.ufes.prontuario.model.Arquivo;
import com.ufes.prontuario.repository.ArquivoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ArquivoService implements IBaseService<ArquivoCadastroDTO, Arquivo> {

    private final ArquivoRepository repository;

    public Arquivo findById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Arquivo", id));
    }

    public List<Arquivo> listar() {
        return this.repository.findAll();
    }

    public Arquivo inserir(ArquivoCadastroDTO arquivoCadastroDTO) {
        return Optional.ofNullable(arquivoCadastroDTO)
                .map(this::validarInsert)
                .map(this::prepareInsert)
                .map(repository::save)
                .orElseThrow();
    }

    public Arquivo update(Long id, ArquivoCadastroDTO arquivoCadastroDTO) {
        return Optional.ofNullable(arquivoCadastroDTO)
                .map(aDto -> validarUpdate(aDto, id))
                .map(arquivo -> prepareUpdate(arquivo ,id))
                .map(this.repository::save)
                .orElseThrow();
    }

    public void delete(Long id) {
        var arquivo = this.findById(id);

        Optional.ofNullable(arquivo)
                .ifPresent(p -> {
                    this.validarDelete(p);
                    this.repository.delete(p);
                });
    }


    @Override
    public ArquivoCadastroDTO validarInsert(ArquivoCadastroDTO dtoCadastro) {
        return dtoCadastro;
    }

    @Override
    public ArquivoCadastroDTO validarUpdate(ArquivoCadastroDTO dtoCadastro, Long id) {
        return dtoCadastro;
    }

    @Override
    public void validarDelete(Arquivo entity) {

    }

    @Override
    public Arquivo prepareInsert(ArquivoCadastroDTO dtoCadastro) {
        return null;
    }

    @Override
    public Arquivo prepareUpdate(ArquivoCadastroDTO dtoCadastro, Long id) {
        return null;
    }
}
