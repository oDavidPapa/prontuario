package com.ufes.prontuario.service;

import com.ufes.prontuario.repository.ResultadoExameRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ResultadoExameService {

    private final ResultadoExameRepository repository;
}
