package com.ufes.prontuario.controller;

import com.ufes.prontuario.dto.alergiapaciente.AlergiaPacienteCadastroDTO;
import com.ufes.prontuario.dto.alergiapaciente.AlergiaPacienteConverter;
import com.ufes.prontuario.dto.alergiapaciente.AlergiaPacienteDTO;
import com.ufes.prontuario.service.AlergiaPacienteService;
import com.ufes.prontuario.util.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/alergias-paciente")
public class AlergiaPacienteController {

    private final AlergiaPacienteService service;


    @GetMapping("/{id}")
    public BaseResponse<AlergiaPacienteDTO> findById(@PathVariable Long id) {
        var alergiaPacienteDTO = Optional.ofNullable(service.findById(id))
                .map(AlergiaPacienteConverter::toDTO).orElse(null);

        return new BaseResponse<>(alergiaPacienteDTO);
    }

    @GetMapping
    public BaseResponse<AlergiaPacienteDTO> filter(@RequestParam Long idPaciente, Pageable pageable) {
        var alergiasPaciente = this.service.filter(idPaciente, pageable)
                .stream().map(AlergiaPacienteConverter::toDTO).toList();

        return new BaseResponse<>(alergiasPaciente, alergiasPaciente.size());
    }

    @PostMapping
    public BaseResponse<AlergiaPacienteDTO> insert(@RequestBody AlergiaPacienteCadastroDTO alergiaPacienteCadastroDTO) {
        var alergiaPacienteDTO = Optional.ofNullable(this.service.inserir(alergiaPacienteCadastroDTO))
                .map(AlergiaPacienteConverter::toDTO).orElse(null);

        return new BaseResponse<>(alergiaPacienteDTO);
    }

    @PutMapping("/{id}")
    public BaseResponse<AlergiaPacienteDTO> update(@PathVariable Long id, @RequestBody AlergiaPacienteCadastroDTO alergiaPacienteCadastroDTO) {
        var alergiaPacienteDTO = Optional.ofNullable(this.service.update(id, alergiaPacienteCadastroDTO))
                .map(AlergiaPacienteConverter::toDTO).orElse(null);

        return new BaseResponse<>(alergiaPacienteDTO);
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Void> delete(@PathVariable Long id) {
        this.service.delete(id);

        return new BaseResponse<>(null);
    }
}
