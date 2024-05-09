package com.ufes.prontuario.controller;

import com.ufes.prontuario.dto.pessoa.PessoaConverter;
import com.ufes.prontuario.dto.pessoa.PessoaDTO;
import com.ufes.prontuario.service.PessoaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pessoa")
public class PessoaController {

    private final PessoaService service;

    @GetMapping("/{id}")
    public ResponseEntity<PessoaDTO> findById(@PathVariable Long id) {

        var pessoaDTO = Optional.ofNullable(service.findById(id))
                .map(PessoaConverter::toDTO).orElse(null);

        return ResponseEntity.ok().body(pessoaDTO);
    }
}
