package com.ufes.prontuario.dto.exame;

import com.ufes.prontuario.config.security.auditoria.Auditoria;
import com.ufes.prontuario.dto.consulta.ConsultaConverter;
import com.ufes.prontuario.model.Exame;

public class ExameConverter {

    public static Exame toEntity(ExameCadastroDTO exameCadastroDTO) {

        var exame = new Exame();
        exame.setDescricao(exameCadastroDTO.getDescricao());
        exame.setAuditoria(new Auditoria());
        return exame;
    }

    public static ExameDTO toDTO(Exame exame) {
        return ExameDTO.builder()
                .id(exame.getId())
                .descricao(exame.getDescricao())
                .consulta(ConsultaConverter.toDTO(exame.getConsulta()))
                .build();
    }
}
