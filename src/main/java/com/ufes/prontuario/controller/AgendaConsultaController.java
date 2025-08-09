package com.ufes.prontuario.controller;

import com.ufes.prontuario.dto.agendaconsulta.AgendaConsultaCadastroDTO;
import com.ufes.prontuario.dto.agendaconsulta.AgendaConsultaConverter;
import com.ufes.prontuario.dto.agendaconsulta.AgendaConsultaDTO;
import com.ufes.prontuario.service.AgendaConsultaService;
import com.ufes.prontuario.util.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/agenda-consulta")
public class AgendaConsultaController {

    private AgendaConsultaService service;

    @Autowired
    public void setService(AgendaConsultaService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public BaseResponse<AgendaConsultaDTO> findById(@PathVariable Long id) {
        var agendaConsultaDTO = Optional.ofNullable(service.findById(id))
                .map(AgendaConsultaConverter::toDTO).orElse(null);

        return new BaseResponse<>(agendaConsultaDTO);
    }

    @PostMapping
    public BaseResponse<AgendaConsultaDTO> insert(@RequestBody AgendaConsultaCadastroDTO agendaConsultaCadastroDTO) {
        var agendaConsultaDTO = Optional.ofNullable(this.service.inserir(agendaConsultaCadastroDTO))
                .map(AgendaConsultaConverter::toDTO).orElse(null);

        return new BaseResponse<>(agendaConsultaDTO);
    }


    @GetMapping
    public BaseResponse<AgendaConsultaDTO> filter(
            @RequestParam(required = false) String nomePaciente,
            @RequestParam(required = false) String nomeMedico,
            @RequestParam(required = false) LocalDate dataInicio,
            @RequestParam(required = false) LocalDate dataFim,
            @RequestParam(required = false) String cpfPaciente,
            Pageable pageable) {

        var agendas = this.service.filter(
                nomePaciente, nomeMedico, dataInicio, dataFim, cpfPaciente, pageable);

        return new BaseResponse<>(agendas.getContent().stream()
                .map(AgendaConsultaConverter::toDTO)
                .collect(Collectors.toList()),
                agendas.getTotalElements());
    }

    @PostMapping("{id}/consulta")
    public BaseResponse<Long> createConsulta(@PathVariable Long id, Authentication auth) {
        var idConsultaCreated = this.service.createConsulta(id, auth);
        return new BaseResponse<>(idConsultaCreated);
    }

}
