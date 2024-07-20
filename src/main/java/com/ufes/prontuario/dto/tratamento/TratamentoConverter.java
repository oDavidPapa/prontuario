package com.ufes.prontuario.dto.tratamento;

import com.ufes.prontuario.config.security.auditoria.Auditoria;
import com.ufes.prontuario.dto.consulta.ConsultaConverter;
import com.ufes.prontuario.model.Tratamento;

public class TratamentoConverter {

    public static Tratamento toEntity(TratamentoCadastroDTO tratamentoCadastroDTO) {

        var tratamento = new Tratamento();
        tratamento.setTratamento(tratamentoCadastroDTO.getTratamento());
        tratamento.setDescricao(tratamentoCadastroDTO.getDescricao());
        tratamento.setAuditoria(new Auditoria());

        return tratamento;
    }

    public static TratamentoDTO toDTO(Tratamento tratamento) {
        return TratamentoDTO.builder()
                .id(tratamento.getId())
                .tratamento(tratamento.getTratamento())
                .descricao(tratamento.getDescricao())
                .consulta(ConsultaConverter.toDTO(tratamento.getConsulta()))
                .build();
    }
}
