package com.ufes.prontuario.service;

import com.ufes.prontuario.repository.PrescricaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PrescricaoService {

    private final PrescricaoRepository repository;
}
