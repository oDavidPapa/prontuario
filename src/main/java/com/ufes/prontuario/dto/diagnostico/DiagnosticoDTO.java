package com.ufes.prontuario.dto.diagnostico;

import com.ufes.prontuario.dto.consulta.ConsultaDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class DiagnosticoDTO {

    private Long id;
    private String descricao;
    private String diagnostico;
    private ConsultaDTO consultaDTO;
}
