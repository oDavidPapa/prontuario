package com.ufes.prontuario.dto.prescricao;

import com.ufes.prontuario.dto.consulta.ConsultaDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class PrescricaoDTO {

    private Long id;
    private ConsultaDTO consultaDTO;
}
