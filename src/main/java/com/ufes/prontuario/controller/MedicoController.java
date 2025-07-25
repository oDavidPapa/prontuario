package com.ufes.prontuario.controller;

import com.ufes.prontuario.dto.medico.MedicoCadastroDTO;
import com.ufes.prontuario.dto.medico.MedicoConverter;
import com.ufes.prontuario.dto.medico.MedicoDTO;
import com.ufes.prontuario.service.MedicoService;
import com.ufes.prontuario.service.PessoaService;
import com.ufes.prontuario.util.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("medicos")
public class MedicoController {

    private final MedicoService service;
    private final PessoaService pessoaService;

    @GetMapping("/{id}")
    public BaseResponse<MedicoDTO> findById(@PathVariable Long id) {
        var medicoDTO = Optional.ofNullable(service.findById(id))
                .map(MedicoConverter::toDTO).orElse(null);

        return new BaseResponse<>(medicoDTO);
    }

    @GetMapping
    public BaseResponse<MedicoDTO> filter(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String cpf,
            @RequestParam(required = false) String especialidade,
            @RequestParam(required = false) String crm,
            Pageable pageable) {

        var medicos = this.service.filter(id, nome, cpf, especialidade, crm, pageable);

        return new BaseResponse<>(medicos.getContent().stream()
                .map(MedicoConverter::toDTO)
                .collect(Collectors.toList()),
                medicos.getTotalElements());
    }

    @GetMapping("/options")
    public BaseResponse<MedicoDTO> findAll() {

        var medicos = this.service.findAll();

        return new BaseResponse<>(medicos.stream()
                .map(MedicoConverter::toDTO)
                .map(service::setContatoPrincial)
                .collect(Collectors.toList()),
                medicos.size());
    }


    @PostMapping
    public BaseResponse<MedicoDTO> insert(@RequestBody MedicoCadastroDTO medicoCadastroDTO) {
        var medicoDTO = Optional.ofNullable(this.service.inserir(medicoCadastroDTO))
                .map(MedicoConverter::toDTO).orElse(null);

        return new BaseResponse<>(medicoDTO);
    }

    @PutMapping("/{id}")
    public BaseResponse<MedicoDTO> update(@PathVariable Long id, @RequestBody MedicoCadastroDTO medicoCadastroDTO) {
        var medicoDTO = Optional.ofNullable(this.service.update(id, medicoCadastroDTO))
                .map(MedicoConverter::toDTO).orElseThrow();
        var pessoaCadastroDTO = medicoCadastroDTO.getPessoaCadastroDTO();
        if(medicoDTO.getPessoa() != null) {
            pessoaService.update(medicoDTO.getPessoa().getId(), pessoaCadastroDTO);
        }
        return new BaseResponse<>(medicoDTO);
    }
}
