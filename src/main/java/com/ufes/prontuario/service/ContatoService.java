package com.ufes.prontuario.service;

import com.ufes.prontuario.dto.contato.ContatoCadastroDTO;
import com.ufes.prontuario.exception.RecursoNaoEncontradoException;
import com.ufes.prontuario.model.Contato;
import com.ufes.prontuario.repository.ContatoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ContatoService implements IBaseService<ContatoCadastroDTO, Contato> {

    private final ContatoRepository repository;

    public Contato findById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Contato", id));
    }

    public List<Contato> listar() {
        return this.repository.findAll();
    }

    public Contato inserir(ContatoCadastroDTO contatoCadastroDTO) {
        return Optional.ofNullable(contatoCadastroDTO)
                .map(this::validarInsert)
                .map(this::prepareInsert)
                .map(repository::save)
                .orElseThrow();
    }

    public Contato update(Long id, ContatoCadastroDTO contatoCadastroDTO) {
        return Optional.ofNullable(contatoCadastroDTO)
                .map(aDto -> validarUpdate(aDto, id))
                .map(contato -> prepareUpdate(contato ,id))
                .map(this.repository::save)
                .orElseThrow();
    }

    public void delete(Long id) {
        var contato = this.findById(id);

        Optional.ofNullable(contato)
                .ifPresent(c -> {
                    this.validarDelete(c);
                    this.repository.delete(c);
                });
    }

    @Override
    public ContatoCadastroDTO validarInsert(ContatoCadastroDTO dtoCadastro) {
        return null;
    }

    @Override
    public ContatoCadastroDTO validarUpdate(ContatoCadastroDTO dtoCadastro, Long id) {
        return null;
    }

    @Override
    public void validarDelete(Contato entity) {

    }

    @Override
    public Contato prepareInsert(ContatoCadastroDTO dtoCadastro) {
        return null;
    }

    @Override
    public Contato prepareUpdate(ContatoCadastroDTO dtoCadastro, Long id) {
        return null;
    }
}
