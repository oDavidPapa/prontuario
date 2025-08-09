package com.ufes.prontuario.controller;

import com.ufes.prontuario.dto.resumoconsulta.ResumoConsultaDTO;
import com.ufes.prontuario.service.ResumoConsultaService;
import com.ufes.prontuario.util.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/resumo-consulta")
public class ResumoConsultaController {

    private final ResumoConsultaService service;

    @GetMapping("/{id}")
    public BaseResponse<ResumoConsultaDTO> getResumoConsulta(@PathVariable Long id) {
        var resumoConsulta = service.getResumoConsulta(id);
        return new BaseResponse<>(resumoConsulta);
    }
}
