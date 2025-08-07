package com.ufes.prontuario.controller;


import com.ufes.prontuario.dto.arquivo.ArquivoConverter;
import com.ufes.prontuario.dto.arquivo.ArquivoDTO;
import com.ufes.prontuario.dto.consulta.ConsultaConverter;
import com.ufes.prontuario.service.ArquivoService;
import com.ufes.prontuario.util.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @GetMapping("/consulta/{idConsulta}")
    public BaseResponse<ArquivoDTO> listarArquivosPorConsulta(@PathVariable Long idConsulta) {
        var arquivos = service.listarPorConsulta(idConsulta);
        return new BaseResponse<>(arquivos.stream()
                .map(ArquivoConverter::toDTO)
                .collect(Collectors.toList()),
                arquivos.size());
    }

    @GetMapping("/{id}/download")
    public ResponseEntity<byte[]> downloadArquivo(@PathVariable Long id) {
        var arquivo = service.findById(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(arquivo.getTipo()));
        headers.setContentDisposition(ContentDisposition
                .attachment()
                .filename(arquivo.getNome())
                .build());
        headers.setContentLength(arquivo.getArquivo().length);

        return new ResponseEntity<>(arquivo.getArquivo(), headers, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Void> deletarArquivo(@PathVariable Long id) {
        service.deletarPorId(id);
        return new BaseResponse<>(null);
    }
}
