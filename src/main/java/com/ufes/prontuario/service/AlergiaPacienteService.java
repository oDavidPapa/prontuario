package com.ufes.prontuario.service;

import com.ufes.prontuario.repository.AlergiaPacienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AlergiaPacienteService {

    private final AlergiaPacienteRepository repository;
}
