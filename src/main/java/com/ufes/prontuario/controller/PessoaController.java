package com.ufes.prontuario.controller;

import com.ufes.prontuario.dto.pessoa.PessoaCadastroDTO;
import com.ufes.prontuario.dto.pessoa.PessoaConverter;
import com.ufes.prontuario.dto.pessoa.PessoaDTO;
import com.ufes.prontuario.service.PessoaService;
import com.ufes.prontuario.util.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("pessoas")
public class PessoaController {

    private final PessoaService service;

    @GetMapping("/{id}")
    public BaseResponse<PessoaDTO> findById(@PathVariable Long id) {
        var pessoaDTO = Optional.ofNullable(service.findById(id))
                .map(PessoaConverter::toDTO).orElse(null);

        return new BaseResponse<>(pessoaDTO);
    }

    @GetMapping
    public BaseResponse<PessoaDTO> filter(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String cpf,
            Pageable pageable) {

        var pessoas = this.service.filter(id, nome, cpf, pageable);

        return new BaseResponse<>(pessoas.getContent().stream()
                .map(PessoaConverter::toDTO)
                .collect(Collectors.toList()),
                pessoas.getTotalElements());
    }


    @PostMapping
    public BaseResponse<PessoaDTO> insert(@RequestBody PessoaCadastroDTO pessoaCadastroDTO) {
        var pessoaDTO = Optional.ofNullable(this.service.inserir(pessoaCadastroDTO))
                .map(PessoaConverter::toDTO).orElse(null);

        return new BaseResponse<>(pessoaDTO);
    }

    @PutMapping("/{id}")
    public BaseResponse<PessoaDTO> update(@PathVariable Long id, @RequestBody PessoaCadastroDTO pessoaCadastroDTO) {
        var pessoaDTO = Optional.ofNullable(this.service.update(id, pessoaCadastroDTO))
                .map(PessoaConverter::toDTO).orElse(null);

        return new BaseResponse<>(pessoaDTO);
    }
}
