package com.ufes.prontuario.controller;

import com.ufes.prontuario.dto.paciente.PacienteCadastroDTO;
import com.ufes.prontuario.dto.paciente.PacienteConverter;
import com.ufes.prontuario.dto.paciente.PacienteDTO;
import com.ufes.prontuario.service.PacienteService;
import com.ufes.prontuario.util.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/paciente")
public class PacienteController {

    private final PacienteService service;

    @GetMapping("/{id}")
    public BaseResponse<PacienteDTO> findById(@PathVariable Long id) {
        var pessoaDTO = Optional.ofNullable(service.findById(id))
                .map(PacienteConverter::toDTO).orElse(null);

        return new BaseResponse<>(pessoaDTO);
    }

    @GetMapping
    public BaseResponse<PacienteDTO> list() {
       var pacientes =  this.service.listar().stream()
                .map(PacienteConverter::toDTO).toList();

        return new BaseResponse<>(pacientes, pacientes.size());
    }

    @PostMapping
    public BaseResponse<PacienteDTO> insert(@RequestBody PacienteCadastroDTO pacienteCadastroDTO) {
        var pacienteDTO = Optional.ofNullable(this.service.inserir(pacienteCadastroDTO))
                .map(PacienteConverter::toDTO).orElse(null);

        return new BaseResponse<>(pacienteDTO);
    }

    @PutMapping("/{id}")
    public BaseResponse<PacienteDTO> update(@PathVariable Long id, @RequestBody PacienteCadastroDTO pacienteCadastroDTO) {
        var pacienteDTO = Optional.ofNullable(this.service.update(id,pacienteCadastroDTO))
                .map(PacienteConverter::toDTO).orElse(null);

        return new BaseResponse<>(pacienteDTO);
    }
}
