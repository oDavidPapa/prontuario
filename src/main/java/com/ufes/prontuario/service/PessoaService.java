package com.ufes.prontuario.service;

import com.ufes.prontuario.dto.pessoa.PessoaCadastroDTO;
import com.ufes.prontuario.exception.RecursoNaoEncontradoException;
import com.ufes.prontuario.model.Pessoa;
import com.ufes.prontuario.repository.PessoaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PessoaService implements IBaseService<PessoaCadastroDTO, Pessoa>{

    private final PessoaRepository repository;

    public Pessoa findById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Pessoa", id));
    }

    @Override
    public PessoaCadastroDTO validarInsert(PessoaCadastroDTO dtoCadastro) {
        return null;
    }

    @Override
    public PessoaCadastroDTO validarUpdate(PessoaCadastroDTO dtoCadastro, Long id) {
        return null;
    }

    @Override
    public void validarDelete(Pessoa entity) {

    }

    @Override
    public Pessoa prepareInsert(PessoaCadastroDTO dtoCadastro) {
        return null;
    }

    @Override
    public Pessoa prepareUpdate(PessoaCadastroDTO dtoCadastro, Long id) {
        return null;
    }
}
