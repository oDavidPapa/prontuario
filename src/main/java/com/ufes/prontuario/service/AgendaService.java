package com.ufes.prontuario.service;

import com.ufes.prontuario.repository.AgendaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AgendaService {

    private final AgendaRepository repository;
}
