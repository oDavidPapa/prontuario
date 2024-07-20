package com.ufes.prontuario.dto.resultadoexame;

import com.ufes.prontuario.config.security.auditoria.Auditoria;
import com.ufes.prontuario.dto.arquivo.ArquivoConverter;
import com.ufes.prontuario.dto.exame.ExameConverter;
import com.ufes.prontuario.model.ResultadoExame;

public class ResultadoExameConverter {

    public static ResultadoExameDTO toDTO(ResultadoExame resultadoExame) {
        return ResultadoExameDTO.builder()
                .id(resultadoExame.getId())
                .exame(ExameConverter.toDTO(resultadoExame.getExame()))
                .arquivo(ArquivoConverter.toDTO(resultadoExame.getArquivo()))
                .descricao(resultadoExame.getDescricao())
                .build();
    }

    public static ResultadoExame toEntity(ResultadoExameCadastroDTO resultadoExameCadastroDTO) {

        var resultadoExame = new ResultadoExame();
        resultadoExame.setDescricao(resultadoExame.getDescricao());
        resultadoExame.setAuditoria(new Auditoria());

        return resultadoExame;
    }
}
