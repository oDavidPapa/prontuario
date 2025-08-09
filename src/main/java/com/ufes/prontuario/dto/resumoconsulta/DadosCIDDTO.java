package com.ufes.prontuario.dto.resumoconsulta;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class DadosCIDDTO {
    private String descricao;
    private String codigo;
}
