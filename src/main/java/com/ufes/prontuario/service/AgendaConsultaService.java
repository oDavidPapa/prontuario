package com.ufes.prontuario.service;


import com.ufes.prontuario.repository.AgendaConsultaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AgendaConsultaService {

    private final AgendaConsultaRepository repository;
}
