package com.ufes.prontuario.service;

import com.ufes.prontuario.repository.DoencaDiagnosticoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DoencaDiagnosticoService {

    private final DoencaDiagnosticoRepository repository;
}
