package com.ufes.prontuario.dto.arquivo;

import com.ufes.prontuario.model.Arquivo;

public class ArquivoConverter {

    public static Arquivo toEntity(ArquivoCadastroDTO arquivoCadastroDTO) {

        var arquivo = new Arquivo();
        arquivo.setArquivo(arquivo.getArquivo());
        arquivo.setNome(arquivo.getNome());

        return arquivo;
    }

    public static ArquivoDTO toDTO(Arquivo arquivo) {
        return ArquivoDTO.builder()
                .id(arquivo.getId())
                .arquivo(arquivo.getArquivo())
                .nome(arquivo.getNome())
                .build();
    }
}
