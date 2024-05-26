package com.ufes.prontuario.controller;

import com.ufes.prontuario.dto.consulta.ConsultaCadastroDTO;
import com.ufes.prontuario.dto.consulta.ConsultaConverter;
import com.ufes.prontuario.dto.consulta.ConsultaDTO;
import com.ufes.prontuario.service.ConsultaService;
import com.ufes.prontuario.util.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/consultas")
public class ConsultaController {

    private final ConsultaService service;

    @GetMapping("/{id}")
    public BaseResponse<ConsultaDTO> findById(@PathVariable Long id) {
        var consultaDTO = Optional.ofNullable(service.findById(id))
                .map(ConsultaConverter::toDTO).orElse(null);

        return new BaseResponse<>(consultaDTO);
    }

    @GetMapping
    public BaseResponse<ConsultaDTO> filter(
            @RequestParam Long idConsulta,
            @RequestParam(required = false) String nomePaciente,
            @RequestParam(required = false) String nomeMedico,
            @RequestParam(required = false) LocalDate dataInicio,
            @RequestParam(required = false) LocalDate dataFim,
            @RequestParam(required = false) String tipoConsulta,
            Pageable pageable) {

        var consultas = this.service.filter(idConsulta,
                nomePaciente, nomeMedico, dataInicio, dataFim, tipoConsulta, pageable);

        return new BaseResponse<>(consultas.getContent().stream()
                .map(ConsultaConverter::toDTO)
                .collect(Collectors.toList()),
                consultas.getTotalElements());
    }

    @PostMapping
    public BaseResponse<ConsultaDTO> insert(@RequestBody ConsultaCadastroDTO consultaCadastroDTO) {
        var consultaDTO = Optional.ofNullable(this.service.inserir(consultaCadastroDTO))
                .map(ConsultaConverter::toDTO).orElse(null);

        return new BaseResponse<>(consultaDTO);
    }


    @PutMapping("/{id}")
    public BaseResponse<ConsultaDTO> update(@PathVariable Long id, @RequestBody ConsultaCadastroDTO consultaCadastroDTO) {
        var consultaDTO = Optional.ofNullable(this.service.update(id, consultaCadastroDTO))
                .map(ConsultaConverter::toDTO).orElse(null);

        return new BaseResponse<>(consultaDTO);
    }
}
