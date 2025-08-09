package com.ufes.prontuario.dto.resumoconsulta;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class DadosPacienteDTO {

    private String nome;
    private Integer idade;
    private String sexo;
}
