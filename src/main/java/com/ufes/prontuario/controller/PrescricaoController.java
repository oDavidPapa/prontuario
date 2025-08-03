package com.ufes.prontuario.controller;

import com.ufes.prontuario.dto.prescricao.PrescricaoCadastroDTO;
import com.ufes.prontuario.dto.prescricao.PrescricaoConverter;
import com.ufes.prontuario.dto.prescricao.PrescricaoDTO;
import com.ufes.prontuario.dto.tratamento.TratamentoConverter;
import com.ufes.prontuario.dto.tratamento.TratamentoDTO;
import com.ufes.prontuario.service.PrescricaoService;
import com.ufes.prontuario.util.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("prescricoes")
public class PrescricaoController {

    private final PrescricaoService service;

    @GetMapping
    public BaseResponse<PrescricaoDTO> filter(
            @RequestParam Long idConsulta,
            Pageable pageable) {

        var prescricoes = this.service.filter(idConsulta, pageable);

        return new BaseResponse<>(prescricoes.getContent().stream()
                .map(PrescricaoConverter::toDTO)
                .collect(Collectors.toList()),
                prescricoes.getTotalElements());
    }

    @PostMapping
    public BaseResponse<PrescricaoDTO> insert(@RequestBody PrescricaoCadastroDTO prescricaoCadastroDTO) {
        var prescricaoDTO = Optional.ofNullable(this.service.inserir(prescricaoCadastroDTO))
                .map(PrescricaoConverter::toDTO).orElse(null);

        return new BaseResponse<>(prescricaoDTO);
    }

    @PutMapping("/{id}")
    public BaseResponse<PrescricaoDTO> update(@PathVariable Long id, @RequestBody PrescricaoCadastroDTO prescricaoCadastroDTO) {
        var medicamentoDTO = Optional.ofNullable(this.service.update(id, prescricaoCadastroDTO))
                .map(PrescricaoConverter::toDTO).orElse(null);

        return new BaseResponse<>(medicamentoDTO);
    }

    @GetMapping("/consulta/{idConsulta}")
    public BaseResponse<PrescricaoDTO> findByConsultaId(@PathVariable Long idConsulta) {
        var prescricaoDTO = Optional.ofNullable(service.findByIdConsulta(idConsulta))
                .map(PrescricaoConverter::toDTO).orElse(null);

        return new BaseResponse<>(prescricaoDTO);
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Void> delete(@PathVariable Long id) {
        this.service.delete(id);

        return new BaseResponse<>(null);
    }
}
