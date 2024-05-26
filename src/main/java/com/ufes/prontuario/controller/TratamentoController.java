package com.ufes.prontuario.controller;

import com.ufes.prontuario.dto.tratamento.TratamentoCadastroDTO;
import com.ufes.prontuario.dto.tratamento.TratamentoConverter;
import com.ufes.prontuario.dto.tratamento.TratamentoDTO;
import com.ufes.prontuario.service.TratamentoService;
import com.ufes.prontuario.util.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("tratamentos")
public class TratamentoController {

    private final TratamentoService service;

    @GetMapping("/{id}")
    public BaseResponse<TratamentoDTO> findById(@PathVariable Long id) {
        var tratamentoDTO = Optional.ofNullable(service.findById(id))
                .map(TratamentoConverter::toDTO).orElse(null);

        return new BaseResponse<>(tratamentoDTO);
    }

    @GetMapping
    public BaseResponse<TratamentoDTO> filter(@RequestParam Long idConsulta, Pageable pageable) {
        var tratamentos = this.service.filter(idConsulta, pageable);

        return new BaseResponse<>(tratamentos.getContent().stream()
                .map(TratamentoConverter::toDTO)
                .collect(Collectors.toList()),
                tratamentos.getTotalElements());
    }

    @PostMapping
    public BaseResponse<TratamentoDTO> insert(@RequestBody TratamentoCadastroDTO tratamentoCadastroDTO) {
        var tratamentoDTO = Optional.ofNullable(this.service.inserir(tratamentoCadastroDTO))
                .map(TratamentoConverter::toDTO).orElse(null);

        return new BaseResponse<>(tratamentoDTO);
    }

    @PutMapping("/{id}")
    public BaseResponse<TratamentoDTO> update(@PathVariable Long id, @RequestBody TratamentoCadastroDTO tratamentoCadastroDTO) {
        var tratamentoDTO = Optional.ofNullable(this.service.update(id, tratamentoCadastroDTO))
                .map(TratamentoConverter::toDTO).orElse(null);

        return new BaseResponse<>(tratamentoDTO);
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Void> delete(@PathVariable Long id) {
        this.service.delete(id);

        return new BaseResponse<>(null);
    }
}
