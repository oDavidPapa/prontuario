package com.ufes.prontuario.controller;

import com.ufes.prontuario.dto.pessoa.PessoaCadastroDTO;
import com.ufes.prontuario.dto.pessoa.PessoaConverter;
import com.ufes.prontuario.dto.pessoa.PessoaDTO;
import com.ufes.prontuario.service.PessoaService;
import com.ufes.prontuario.util.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pessoa")
public class PessoaController {

    private final PessoaService service;

    @GetMapping("/{id}")
    public BaseResponse<PessoaDTO> findById(@PathVariable Long id) {
        var pessoaDTO = Optional.ofNullable(service.findById(id))
                .map(PessoaConverter::toDTO).orElse(null);

        return new BaseResponse<>(pessoaDTO);
    }

    @GetMapping
    public BaseResponse<PessoaDTO> list() {
        var pessoas =  this.service.listar().stream()
                .map(PessoaConverter::toDTO).toList();

        return new BaseResponse<>(pessoas, pessoas.size());
    }

    @PostMapping
    public BaseResponse<PessoaDTO> insert(@RequestBody PessoaCadastroDTO pessoaCadastroDTO) {
        var pessoaDTO = Optional.ofNullable(this.service.inserir(pessoaCadastroDTO))
                .map(PessoaConverter::toDTO).orElse(null);

        return new BaseResponse<>(pessoaDTO);
    }

    @PutMapping("/{id}")
    public BaseResponse<PessoaDTO> update(@PathVariable Long id, @RequestBody PessoaCadastroDTO pessoaCadastroDTO) {
        var pessoaDTO = Optional.ofNullable(this.service.update(id,pessoaCadastroDTO))
                .map(PessoaConverter::toDTO).orElse(null);

        return new BaseResponse<>(pessoaDTO);
    }
}
