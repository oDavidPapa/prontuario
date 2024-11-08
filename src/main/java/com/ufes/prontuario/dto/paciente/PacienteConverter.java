package com.ufes.prontuario.dto.paciente;

import com.ufes.prontuario.config.security.auditoria.Auditoria;
import com.ufes.prontuario.dto.pessoa.PessoaConverter;
import com.ufes.prontuario.model.Paciente;

public class PacienteConverter {

    public static PacienteDTO toDTO(Paciente paciente) {
        return PacienteDTO.builder()
                .id(paciente.getId())
                .altura(paciente.getAltura())
                .pessoa(PessoaConverter.toDTO(paciente.getPessoa()))
                .peso(paciente.getPeso())
                .build();
    }

    public static Paciente toEntity(PacienteCadastroDTO pacienteCadastroDTO) {
        return Paciente.builder()
                .peso(pacienteCadastroDTO.getPeso())
                .altura(pacienteCadastroDTO.getAltura())
                .auditoria(new Auditoria())
                .build();
    }

}
