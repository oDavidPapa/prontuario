package com.ufes.prontuario.dto.doencadiagnostico;

import com.ufes.prontuario.dto.diagnostico.DiagnosticoDTO;
import com.ufes.prontuario.dto.doenca.DoencaDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class DoencaDiagnosticoDTO {

    private Long id;
    private DoencaDTO doenca;
    private DiagnosticoDTO diagnostico;
}
