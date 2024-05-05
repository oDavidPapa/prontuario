package com.ufes.prontuario.service;

import com.ufes.prontuario.repository.DoencaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DoencaService {

    private final DoencaRepository repository;
}

