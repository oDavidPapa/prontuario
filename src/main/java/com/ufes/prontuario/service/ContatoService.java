package com.ufes.prontuario.service;

import com.ufes.prontuario.repository.ContatoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ContatoService {

    private final ContatoRepository repository;
}
