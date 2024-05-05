package com.ufes.prontuario.service;

import com.ufes.prontuario.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository repository;

}
