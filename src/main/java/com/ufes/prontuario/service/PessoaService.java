package com.ufes.prontuario.service;

import com.ufes.prontuario.repository.PessoaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PessoaService {

    private final PessoaRepository repository;
}
