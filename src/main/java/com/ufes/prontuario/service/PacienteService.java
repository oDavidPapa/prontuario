package com.ufes.prontuario.service;

import com.ufes.prontuario.repository.PacienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PacienteService {

    private final PacienteRepository repository;
}
