package com.ufes.prontuario.dto.tratamento;

import com.ufes.prontuario.dto.consulta.ConsultaDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class TratamentoDTO {

    private Long id;
    private String tratamento;
    private String descricao;
    private ConsultaDTO consulta;
}
