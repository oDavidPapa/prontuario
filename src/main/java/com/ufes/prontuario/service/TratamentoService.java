package com.ufes.prontuario.service;

import com.ufes.prontuario.repository.TratamentoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TratamentoService {

    private final TratamentoRepository repository;
}
