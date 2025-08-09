package com.ufes.prontuario.dto.resumoconsulta;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class DadosConsultaDTO {
    private Long idConsulta;
    private String tipo;
    private String medico;
    private String especialidade;
    private String dataHora;

}
