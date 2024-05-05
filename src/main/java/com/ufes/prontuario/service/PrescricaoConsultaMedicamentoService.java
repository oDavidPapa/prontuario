package com.ufes.prontuario.service;

import com.ufes.prontuario.repository.PrescricaoConsultaMedicamentoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PrescricaoConsultaMedicamentoService {

    private final PrescricaoConsultaMedicamentoRepository repository;
}
