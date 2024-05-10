package com.ufes.prontuario.dto.prescricao;

import com.ufes.prontuario.dto.consulta.ConsultaConverter;
import com.ufes.prontuario.model.Prescricao;

public class PrescricaoConverter {

    public static PrescricaoDTO toDTO(Prescricao prescricao) {
        return PrescricaoDTO.builder()
                .id(prescricao.getId())
                .consultaDTO(ConsultaConverter.toDTO(prescricao.getConsulta()))
                .build();
    }

    public static Prescricao toEntity(PrescricaoCadastroDTO prescricaoCadastroDTO) {
        return new Prescricao();
    }
}
