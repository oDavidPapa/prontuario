package com.ufes.prontuario.controller;


import com.ufes.prontuario.dto.arquivo.ArquivoConverter;
import com.ufes.prontuario.dto.arquivo.ArquivoDTO;
import com.ufes.prontuario.service.ArquivoService;
import com.ufes.prontuario.util.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("arquivos")
public class ArquivoController {

    private final ArquivoService service;

    @PostMapping("/upload")
    public BaseResponse<ArquivoDTO> uploadArquivo(
            @RequestParam("arquivo") MultipartFile arquivo,
            @RequestParam("descricao") String descricao,
            @RequestParam("idConsulta") Long idConsulta,
            @RequestParam("nome") String nome) {
        var arquivoDTO = Optional.ofNullable(service.upload(arquivo, nome, descricao, idConsulta))
                .map(ArquivoConverter::toDTO).orElse(null);
        return new BaseResponse<>(arquivoDTO);
    }
}
