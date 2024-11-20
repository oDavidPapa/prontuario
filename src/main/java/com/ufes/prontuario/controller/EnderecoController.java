package com.ufes.prontuario.controller;

import com.ufes.prontuario.dto.diagnostico.DiagnosticoCadastroDTO;
import com.ufes.prontuario.dto.diagnostico.DiagnosticoConverter;
import com.ufes.prontuario.dto.diagnostico.DiagnosticoDTO;
import com.ufes.prontuario.dto.endereco.EnderecoCadastroDTO;
import com.ufes.prontuario.dto.endereco.EnderecoConverter;
import com.ufes.prontuario.dto.endereco.EnderecoDTO;
import com.ufes.prontuario.model.Endereco;
import com.ufes.prontuario.service.EnderecoSerivce;
import com.ufes.prontuario.util.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("enderecos")
public class EnderecoController {

    private final EnderecoSerivce service;

    @GetMapping("/{id}")
    public BaseResponse<EnderecoDTO> findById(@PathVariable Long id) {
        var enderecoDTO = Optional.ofNullable(service.findById(id))
                .map(EnderecoConverter::toDTO).orElse(null);

        return new BaseResponse<>(enderecoDTO);
    }

    @GetMapping("pessoa/{idPessoa}")
    public BaseResponse<EnderecoDTO> findByPessoaId(@PathVariable Long idPessoa) {
        var enderecoDTO = Optional.ofNullable(service.findByPessoaId(idPessoa))
                .map(EnderecoConverter::toDTO).orElse(null);

        return new BaseResponse<>(enderecoDTO);
    }

    @PostMapping
    public BaseResponse<EnderecoDTO> insert(@RequestBody EnderecoCadastroDTO enderecoCadastroDTO) {
        var enderecoDTO = Optional.ofNullable(this.service.inserir(enderecoCadastroDTO))
                .map(EnderecoConverter::toDTO).orElse(null);

        return new BaseResponse<>(enderecoDTO);
    }

    @PutMapping("/{id}")
    public BaseResponse<EnderecoDTO> update(@PathVariable Long id, @RequestBody EnderecoCadastroDTO enderecoCadastroDTO) {
        var enderecoDTO = Optional.ofNullable(this.service.update(id, enderecoCadastroDTO))
                .map(EnderecoConverter::toDTO).orElse(null);

        return new BaseResponse<>(enderecoDTO);
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Void> delete(@PathVariable Long id) {
        this.service.delete(id);

        return new BaseResponse<>(null);
    }
}
