package com.ufes.prontuario.service;

import com.ufes.prontuario.repository.ExameRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ExameService {

    private final ExameRepository repository;

}
