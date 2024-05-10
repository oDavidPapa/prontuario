package com.ufes.prontuario.dto.alergiapaciente;

import com.ufes.prontuario.dto.paciente.PacienteConverter;
import com.ufes.prontuario.model.AlergiaPaciente;

public class AlergiaPacienteConverter {

    public static AlergiaPaciente toEntity(AlergiaPacienteCadastroDTO alergiaPacienteCadastroDTO) {
        var alergiaPaciente = new AlergiaPaciente();
        alergiaPaciente.setDescricao(alergiaPaciente.getDescricao());

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
