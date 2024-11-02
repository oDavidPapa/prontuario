package com.ufes.prontuario.dto.diagnostico;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class DiagnosticoCadastroDTO {

    private String diagnostico;
    private Long idConsulta;
}
