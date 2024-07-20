package com.ufes.prontuario.dto.diagnostico;

import com.ufes.prontuario.config.security.auditoria.Auditoria;
import com.ufes.prontuario.dto.consulta.ConsultaConverter;
import com.ufes.prontuario.model.Diagnostico;

public class DiagnosticoConverter {

    public static Diagnostico toEntity(DiagnosticoCadastroDTO diagnosticoCadastroDTO) {

        var diagnostico = new Diagnostico();
        diagnostico.setDiagnostico(diagnosticoCadastroDTO.getDiagnostico());
        diagnostico.setDescricao(diagnosticoCadastroDTO.getDescricao());
        diagnostico.setAuditoria(new Auditoria());
        return diagnostico;
    }

    public static DiagnosticoDTO toDTO(Diagnostico diagnostico) {
        return DiagnosticoDTO.builder()
                .id(diagnostico.getId())
                .descricao(diagnostico.getDescricao())
                .diagnostico(diagnostico.getDiagnostico())
                .consultaDTO(ConsultaConverter.toDTO(diagnostico.getConsulta()))
                .build();
    }
}
