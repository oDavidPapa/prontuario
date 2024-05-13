package com.ufes.prontuario.dto.consulta;

import com.ufes.prontuario.dto.agendaconsulta.AgendaConsultaConverter;
import com.ufes.prontuario.dto.medico.MedicoConverter;
import com.ufes.prontuario.dto.paciente.PacienteConverter;
import com.ufes.prontuario.enums.TipoConsultaEnum;
import com.ufes.prontuario.model.Consulta;

import java.util.Optional;

public class ConsultaConverter {

    public static Consulta toEntity(ConsultaCadastroDTO consultaCadastroDTO) {

        var consulta = new Consulta();
        consulta.setData(consultaCadastroDTO.getData());
        consulta.setMotivo(consultaCadastroDTO.getMotivo());
        consulta.setTipo(TipoConsultaEnum.valueOf(consultaCadastroDTO.getTipo()));

        return consulta;
    }

    public static ConsultaDTO toDTO(Consulta consulta) {
        return ConsultaDTO.builder()
                .id(consulta.getId())
                .data(consulta.getData())
                .motivo(consulta.getMotivo())
                .tipo(consulta.getTipo().name())
                .agendaConsulta(Optional.ofNullable(consulta.getAgendaConsulta())
                        .map(AgendaConsultaConverter::toDTO)
                        .orElse(null))
                .medico(MedicoConverter.toDTO(consulta.getMedico()))
                .paciente(PacienteConverter.toDTO(consulta.getPaciente()))
                .build();
    }
}
