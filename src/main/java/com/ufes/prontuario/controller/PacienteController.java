package com.ufes.prontuario.controller;

import com.ufes.prontuario.dto.paciente.PacienteCadastroDTO;
import com.ufes.prontuario.dto.paciente.PacienteConverter;
import com.ufes.prontuario.dto.paciente.PacienteDTO;
import com.ufes.prontuario.service.PacienteService;
import com.ufes.prontuario.util.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("pacientes")
public class PacienteController {

    private final PacienteService service;

    @GetMapping("/{id}")
    public BaseResponse<PacienteDTO> findById(@PathVariable Long id) {
        var pessoaDTO = Optional.ofNullable(service.findById(id))
                .map(PacienteConverter::toDTO).orElse(null);

        return new BaseResponse<>(pessoaDTO);
    }

    @GetMapping
    public BaseResponse<PacienteDTO> filter(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String cpf,
            @RequestParam(required = false) Long id,
            Pageable pageable) {

        var pacientes = this.service.filter(id, nome, cpf, pageable);

        return new BaseResponse<>(pacientes.stream()
                .map(PacienteConverter::toDTO).toList(),
                pacientes.getTotalElements());
    }

    @PostMapping
    public BaseResponse<PacienteDTO> insert(@RequestBody PacienteCadastroDTO pacienteCadastroDTO) {
        var pacienteDTO = Optional.ofNullable(this.service.inserir(pacienteCadastroDTO))
                .map(PacienteConverter::toDTO).orElse(null);

        return new BaseResponse<>(pacienteDTO);
    }

    @PutMapping("/{id}")
    public BaseResponse<PacienteDTO> update(@PathVariable Long id, @RequestBody PacienteCadastroDTO pacienteCadastroDTO) {
        var pacienteDTO = Optional.ofNullable(this.service.update(id, pacienteCadastroDTO))
                .map(PacienteConverter::toDTO).orElse(null);

        return new BaseResponse<>(pacienteDTO);
    }
}
