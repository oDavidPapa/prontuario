package com.ufes.prontuario.dto.consulta;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ConsultaCadastroDTO {

    private String anamnese;
    private String tipo;
    private Long idMedico;
    private Long idPaciente;
    private Long idConsulta;
    private Long idAgendaConsulta;
    private String loginMedico;
}
