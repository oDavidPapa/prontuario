package com.ufes.prontuario.controller;

import com.ufes.prontuario.dto.exame.ExameCadastroDTO;
import com.ufes.prontuario.dto.exame.ExameConverter;
import com.ufes.prontuario.dto.exame.ExameDTO;
import com.ufes.prontuario.service.ExameService;
import com.ufes.prontuario.util.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("exame-consulta")
public class ExameConsultaController {

   private final ExameService service;

    @PostMapping
    public BaseResponse<ExameDTO> insert(@RequestBody ExameCadastroDTO exameCadastroDTO) {
        var exameDTO = Optional.ofNullable(this.service.inserir(exameCadastroDTO))
                .map(ExameConverter::toDTO).orElse(null);

        return new BaseResponse<>(exameDTO);
    }

    @GetMapping
    public BaseResponse<ExameDTO> filter(@RequestParam Long idConsulta) {
        var examesConsulta = this.service.findAllByConsulta(idConsulta);

        return new BaseResponse<>(examesConsulta.stream()
                .map(ExameConverter::toDTO)
                .collect(Collectors.toList()),
                examesConsulta.size());
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Void> delete(@PathVariable Long id) {
        this.service.delete(id);
        return new BaseResponse<>(null);
    }
}
