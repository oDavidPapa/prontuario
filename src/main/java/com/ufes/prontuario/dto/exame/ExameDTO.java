package com.ufes.prontuario.dto.exame;

import com.ufes.prontuario.dto.consulta.ConsultaDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ExameDTO {

    private Long id;
    private String descricao;
    private ConsultaDTO consulta;
}
