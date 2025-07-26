package com.ufes.prontuario.dto.agendaconsulta;

import com.ufes.prontuario.dto.agenda.AgendaDTO;
import com.ufes.prontuario.dto.medico.MedicoDTO;
import com.ufes.prontuario.dto.paciente.PacienteDTO;
import com.ufes.prontuario.enums.TipoConsultaEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AgendaConsultaDTO {

    private Long id;
    private AgendaDTO agenda;
    private PacienteDTO paciente;
    private MedicoDTO medico;
    private String tipoConsultaDescricao;
    private String tipoConsulta;
    private String dataConsulta;
    private String descricao;
}
