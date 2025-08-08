package com.ufes.prontuario.dto.arquivo;

import com.ufes.prontuario.model.Arquivo;

import java.io.IOException;

public class ArquivoConverter {

    public static Arquivo toEntity(ArquivoCadastroDTO arquivoCadastroDTO) throws IOException {

        var arquivo = new Arquivo();
        arquivo.setArquivo(arquivoCadastroDTO.getArquivo().getBytes());
        arquivo.setNome(arquivoCadastroDTO.getNome());
        arquivo.setDescricao(arquivoCadastroDTO.getDescricao());
        arquivo.setTipo(arquivoCadastroDTO.getArquivo().getContentType());

        return arquivo;
    }

    public static ArquivoDTO toDTO(Arquivo arquivo) {
        return ArquivoDTO.builder()
                .id(arquivo.getId())
                .descricao(arquivo.getDescricao())
                .tipo(arquivo.getTipo())
                .idConsulta(arquivo.getConsulta().getId())
                .nome(arquivo.getNome())
                .build();
    }
}
