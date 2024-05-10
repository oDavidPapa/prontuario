package com.ufes.prontuario.dto.alergiapaciente;

import com.ufes.prontuario.dto.paciente.PacienteDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AlergiaPacienteDTO {

    private Long id;
    private String descricao;
    private PacienteDTO paciente;

}
