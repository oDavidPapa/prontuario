package com.ufes.prontuario.service;

import com.ufes.prontuario.repository.MedicamentoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MedicamentoService {

    private final MedicamentoRepository repository;
}
