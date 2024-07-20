package com.ufes.prontuario.dto.doencadiagnostico;

import com.ufes.prontuario.config.security.auditoria.Auditoria;
import com.ufes.prontuario.dto.diagnostico.DiagnosticoConverter;
import com.ufes.prontuario.dto.doenca.DoencaConverter;
import com.ufes.prontuario.model.DoencaDiagnostico;

public class DoencaDiagnosticoConverter {

    public static DoencaDiagnostico toEntity(DoencaDiagnosticoCadastroDTO doencaDiagnosticoCadastroDTO) {
        var doencaDiagnostico = new DoencaDiagnostico();
        doencaDiagnostico.setAuditoria(new Auditoria());
        return new DoencaDiagnostico();
    }

    public static DoencaDiagnosticoDTO toDTO(DoencaDiagnostico doencaDiagnostico) {
        return DoencaDiagnosticoDTO.builder()
                .id(doencaDiagnostico.getId())
                .diagnostico(DiagnosticoConverter.toDTO(doencaDiagnostico.getDiagnostico()))
                .doenca(DoencaConverter.toDTO(doencaDiagnostico.getDoenca()))
                .build();
    }
}
