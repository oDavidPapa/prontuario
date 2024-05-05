package com.ufes.prontuario.service;

import com.ufes.prontuario.repository.DiagnosticoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DiagnosticoService {

    private final DiagnosticoRepository repository;
}
