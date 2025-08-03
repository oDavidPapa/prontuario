package com.ufes.prontuario.dto.tratamento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class TratamentoCadastroDTO {

    private String tratamento;
    private Long idConsulta;
}
