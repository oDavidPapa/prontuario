package com.ufes.prontuario.service;

import com.ufes.prontuario.repository.MedicoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MedicoService {

    private final MedicoRepository repository;
}
