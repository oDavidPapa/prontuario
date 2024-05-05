package com.ufes.prontuario.service;

import com.ufes.prontuario.repository.ConsultaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ConsultaService {

    private ConsultaRepository repository;
}
