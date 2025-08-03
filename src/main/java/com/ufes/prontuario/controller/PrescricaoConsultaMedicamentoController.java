package com.ufes.prontuario.controller;

import com.ufes.prontuario.dto.prescricaomedicamento.PrescricaoConsultaMedicamentoCadastroDTO;
import com.ufes.prontuario.dto.prescricaomedicamento.PrescricaoConsultaMedicamentoConverter;
import com.ufes.prontuario.dto.prescricaomedicamento.PrescricaoConsultaMedicamentoDTO;
import com.ufes.prontuario.dto.tratamento.TratamentoConverter;
import com.ufes.prontuario.dto.tratamento.TratamentoDTO;
import com.ufes.prontuario.service.PrescricaoConsultaMedicamentoService;
import com.ufes.prontuario.util.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("prescricoes/medicamentos")
public class PrescricaoConsultaMedicamentoController {

    private final PrescricaoConsultaMedicamentoService service;

    @GetMapping("/{id}")
    public BaseResponse<PrescricaoConsultaMedicamentoDTO> findById(@PathVariable Long id) {
        var prescricaoConsultaMedicamentoDTO = Optional.ofNullable(service.findById(id))
                .map(PrescricaoConsultaMedicamentoConverter::toDTO).orElse(null);

        return new BaseResponse<>(prescricaoConsultaMedicamentoDTO);
    }

    @GetMapping
    public BaseResponse<PrescricaoConsultaMedicamentoDTO> filter(@RequestParam Long idPrescricao) {
        var medicamentos = Optional.ofNullable(service.findAllByPrescricao(idPrescricao)).orElse(new ArrayList<>());

        return new BaseResponse<>(medicamentos.stream()
                .map(PrescricaoConsultaMedicamentoConverter::toDTO)
                .collect(Collectors.toList()),
                medicamentos.size());
    }

    @PostMapping
    public BaseResponse<PrescricaoConsultaMedicamentoDTO> insert(@RequestBody PrescricaoConsultaMedicamentoCadastroDTO prescricaoMedicamentoCadastroDTO) {
        var prescricaoMedicamentoDTO = Optional.ofNullable(this.service.inserir(prescricaoMedicamentoCadastroDTO))
                .map(PrescricaoConsultaMedicamentoConverter::toDTO).orElse(null);

        return new BaseResponse<>(prescricaoMedicamentoDTO);
    }

    @PutMapping("/{id}")
    public BaseResponse<PrescricaoConsultaMedicamentoDTO> update(@PathVariable Long id, @RequestBody PrescricaoConsultaMedicamentoCadastroDTO prescricaoMedicamentoCadastroDTO) {
        var prescricaoMedicamentoDTO = Optional.ofNullable(this.service.update(id, prescricaoMedicamentoCadastroDTO))
                .map(PrescricaoConsultaMedicamentoConverter::toDTO).orElse(null);

        return new BaseResponse<>(prescricaoMedicamentoDTO);
    }


    @DeleteMapping("/{id}")
    public BaseResponse<Void> delete(@PathVariable Long id) {
        this.service.delete(id);

        return new BaseResponse<>(null);
    }
}
