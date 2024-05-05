package com.ufes.prontuario.service;

import com.ufes.prontuario.repository.ArquivoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ArquivoService {

    private final ArquivoRepository repository;
}
