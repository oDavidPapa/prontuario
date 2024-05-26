package com.ufes.prontuario.controller;

import com.ufes.prontuario.dto.medicamento.MedicamentoCadastroDTO;
import com.ufes.prontuario.dto.medicamento.MedicamentoConverter;
import com.ufes.prontuario.dto.medicamento.MedicamentoDTO;
import com.ufes.prontuario.service.MedicamentoService;
import com.ufes.prontuario.util.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("medicamentos")
public class MedicamentoController {

    private final MedicamentoService service;

    @GetMapping("/{id}")
    public BaseResponse<MedicamentoDTO> findById(@PathVariable Long id) {
        var medicamentoDTO = Optional.ofNullable(service.findById(id))
                .map(MedicamentoConverter::toDTO).orElse(null);

        return new BaseResponse<>(medicamentoDTO);
    }

    @GetMapping
    public BaseResponse<MedicamentoDTO> filter(
            @RequestParam Long idPessoa,
            @RequestParam(required = false) String tipoContato,
            Pageable pageable) {

        var medicamentos = this.service.filter(idPessoa, tipoContato, pageable);

        return new BaseResponse<>(medicamentos.getContent().stream()
                .map(MedicamentoConverter::toDTO)
                .collect(Collectors.toList()),
                medicamentos.getTotalElements());
    }


    @PostMapping
    public BaseResponse<MedicamentoDTO> insert(@RequestBody MedicamentoCadastroDTO medicamentoCadastroDTO) {
        var medicamentoDTO = Optional.ofNullable(this.service.inserir(medicamentoCadastroDTO))
                .map(MedicamentoConverter::toDTO).orElse(null);

        return new BaseResponse<>(medicamentoDTO);
    }

    @PutMapping("/{id}")
    public BaseResponse<MedicamentoDTO> update(@PathVariable Long id, @RequestBody MedicamentoCadastroDTO medicamentoCadastroDTO) {
        var medicamentoDTO = Optional.ofNullable(this.service.update(id, medicamentoCadastroDTO))
                .map(MedicamentoConverter::toDTO).orElse(null);

        return new BaseResponse<>(medicamentoDTO);
    }


    @DeleteMapping("/{id}")
    public BaseResponse<Void> delete(@PathVariable Long id) {
        this.service.delete(id);

        return new BaseResponse<>(null);
    }
}
