package com.ufes.prontuario.dto.alergiapaciente;

import com.ufes.prontuario.config.security.auditoria.Auditoria;
import com.ufes.prontuario.dto.paciente.PacienteConverter;
import com.ufes.prontuario.model.AlergiaPaciente;

public class AlergiaPacienteConverter {

    public static AlergiaPaciente toEntity(AlergiaPacienteCadastroDTO alergiaPacienteCadastroDTO) {
        var alergiaPaciente = new AlergiaPaciente();
        alergiaPaciente.setDescricao(alergiaPacienteCadastroDTO.getDescricao());
        alergiaPaciente.setAuditoria(new Auditoria());

        return alergiaPaciente;
    }

    public static AlergiaPacienteDTO toDTO(AlergiaPaciente alergiaPaciente) {
        return AlergiaPacienteDTO.builder()
                .id(alergiaPaciente.getId())
                .descricao(alergiaPaciente.getDescricao())
                .paciente(PacienteConverter.toDTO(alergiaPaciente.getPaciente()))
                .build();
    }
}
