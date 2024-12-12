package com.ufes.prontuario.controller;

import com.ufes.prontuario.dto.alergiapaciente.AlergiaPacienteCadastroDTO;
import com.ufes.prontuario.dto.alergiapaciente.AlergiaPacienteConverter;
import com.ufes.prontuario.dto.alergiapaciente.AlergiaPacienteDTO;
import com.ufes.prontuario.dto.consulta.ConsultaConverter;
import com.ufes.prontuario.dto.consulta.ConsultaDTO;
import com.ufes.prontuario.dto.doenca.CidCadastroDTO;
import com.ufes.prontuario.dto.doenca.CidConverter;
import com.ufes.prontuario.dto.doenca.CidDTO;
import com.ufes.prontuario.service.CidService;
import com.ufes.prontuario.util.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cids")
public class CidController {

    private final CidService service;

    @GetMapping
    public BaseResponse<CidDTO> filter(@RequestParam Long idDiagnostico, Pageable pageable) {

        var cids = this.service.findByDiagnostico(idDiagnostico);

        return new BaseResponse<>(cids.stream()
                .map(CidConverter::toDTO)
                .collect(Collectors.toList()),
                cids.size());
    }

    @PostMapping
    public BaseResponse<CidDTO> insert(@RequestBody CidCadastroDTO cidCadastroDTO) {
        var cid = Optional.ofNullable(this.service.inserir(cidCadastroDTO))
                .map(CidConverter::toDTO).orElse(null);

        return new BaseResponse<>(cid);
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Void> delete(@PathVariable Long id) {
        this.service.delete(id);

        return new BaseResponse<>(null);
    }
}
