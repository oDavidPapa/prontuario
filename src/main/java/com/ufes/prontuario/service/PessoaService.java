package com.ufes.prontuario.service;

import com.ufes.prontuario.exception.RecursoNaoEncontradoException;
import com.ufes.prontuario.model.Pessoa;
import com.ufes.prontuario.repository.PessoaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PessoaService {

    private final PessoaRepository repository;

    public Pessoa findById(Long id) {

        return this.repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Pessoa", id));
    }
}
