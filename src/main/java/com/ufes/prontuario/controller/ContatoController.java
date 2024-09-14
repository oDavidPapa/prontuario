package com.ufes.prontuario.controller;

import com.ufes.prontuario.dto.contato.ContatoCadastroDTO;
import com.ufes.prontuario.dto.contato.ContatoConverter;
import com.ufes.prontuario.dto.contato.ContatoDTO;
import com.ufes.prontuario.service.ContatoService;
import com.ufes.prontuario.util.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("contatos")
public class ContatoController {

    private final ContatoService service;

    @GetMapping("/{id}")
    public BaseResponse<ContatoDTO> findById(@PathVariable Long id) {
        var contatoDTO = Optional.ofNullable(service.findById(id))
                .map(ContatoConverter::toDTO).orElse(null);

        return new BaseResponse<>(contatoDTO);
    }

    @GetMapping
    public BaseResponse<ContatoDTO> filter(
            @RequestParam Long idPessoa,
            @RequestParam(required = false) String tipoContato,
            Pageable pageable) {

        var contatos = this.service.filter(idPessoa, tipoContato, pageable);

        return new BaseResponse<>(contatos.getContent().stream()
                .map(ContatoConverter::toDTO)
                .collect(Collectors.toList()),
                contatos.getTotalElements());
    }


    @PostMapping
    public BaseResponse<ContatoDTO> insert(@RequestBody ContatoCadastroDTO contatoCadastroDTO) {
        var contatoDTO = Optional.ofNullable(this.service.inserir(contatoCadastroDTO))
                .map(ContatoConverter::toDTO).orElse(null);

        return new BaseResponse<>(contatoDTO);
    }

    @PutMapping("/{id}")
    public BaseResponse<ContatoDTO> update(@PathVariable Long id, @RequestBody ContatoCadastroDTO contatoCadastroDTO) {
        var contatoDTO = Optional.ofNullable(this.service.update(id, contatoCadastroDTO))
                .map(ContatoConverter::toDTO).orElse(null);

        return new BaseResponse<>(contatoDTO);
    }


    @DeleteMapping("/{id}")
    public BaseResponse<Void> delete(@PathVariable Long id) {
        this.service.delete(id);

        return new BaseResponse<>(null);
    }
}
