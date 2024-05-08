package com.ufes.prontuario.service;

import com.ufes.prontuario.dto.pessoa.PessoaConverter;
import com.ufes.prontuario.dto.pessoa.PessoaDTO;
import com.ufes.prontuario.exception.RecursoNaoEncontradoException;
import com.ufes.prontuario.repository.PessoaRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class PessoaService {

    private final PessoaRepository repository;

    public PessoaDTO findById(Long id) {

        return this.repository.findById(id)
                .map(PessoaConverter::toDTO)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Pessoa", id));
    }
}
