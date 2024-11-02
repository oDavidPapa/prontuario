package com.ufes.prontuario.controller;

import com.ufes.prontuario.dto.diagnostico.DiagnosticoCadastroDTO;
import com.ufes.prontuario.dto.diagnostico.DiagnosticoConverter;
import com.ufes.prontuario.dto.diagnostico.DiagnosticoDTO;
import com.ufes.prontuario.service.DiagnosticoService;
import com.ufes.prontuario.util.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("diagnosticos")
public class DiagnosticoController {

    private final DiagnosticoService service;

    @GetMapping("/{id}")
    public BaseResponse<DiagnosticoDTO> findById(@PathVariable Long id) {
        var diagnosticoDTO = Optional.ofNullable(service.findById(id))
                .map(DiagnosticoConverter::toDTO).orElse(null);

        return new BaseResponse<>(diagnosticoDTO);
    }

    @GetMapping("/consulta/{idConsulta}")
    public BaseResponse<DiagnosticoDTO> findByConsultaId(@PathVariable Long idConsulta) {
        var diagnosticoDTO = Optional.ofNullable(service.findByIdConsulta(idConsulta))
                .map(DiagnosticoConverter::toDTO).orElse(null);

        return new BaseResponse<>(diagnosticoDTO);
    }

    @GetMapping
    public BaseResponse<DiagnosticoDTO> filter(
            @RequestParam Long idConsulta,
            @RequestParam(required = false) String descricao,
            Pageable pageable) {

        var diagnosticos = this.service.filter(idConsulta, descricao, pageable);

        return new BaseResponse<>(diagnosticos.getContent().stream()
                .map(DiagnosticoConverter::toDTO)
                .collect(Collectors.toList()),
                diagnosticos.getTotalElements());
    }


    @PostMapping
    public BaseResponse<DiagnosticoDTO> insert(@RequestBody DiagnosticoCadastroDTO diagnosticoCadastroDTO) {
        var diagnosticoDTO = Optional.ofNullable(this.service.inserir(diagnosticoCadastroDTO))
                .map(DiagnosticoConverter::toDTO).orElse(null);

        return new BaseResponse<>(diagnosticoDTO);
    }

    @PutMapping("/{id}")
    public BaseResponse<DiagnosticoDTO> update(@PathVariable Long id, @RequestBody DiagnosticoCadastroDTO diagnosticoCadastroDTO) {
        var diagnosticoDTO = Optional.ofNullable(this.service.update(id, diagnosticoCadastroDTO))
                .map(DiagnosticoConverter::toDTO).orElse(null);

        return new BaseResponse<>(diagnosticoDTO);
    }


    @DeleteMapping("/{id}")
    public BaseResponse<Void> delete(@PathVariable Long id) {
        this.service.delete(id);

        return new BaseResponse<>(null);
    }
}
