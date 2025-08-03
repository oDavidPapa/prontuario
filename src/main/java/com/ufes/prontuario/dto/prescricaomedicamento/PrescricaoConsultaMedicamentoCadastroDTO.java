package com.ufes.prontuario.dto.prescricaomedicamento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class PrescricaoConsultaMedicamentoCadastroDTO {

    private String medicamento;
    private String instrucaoUso;
    private Long idPrescricao;
    private String observacao;
}
